package xxl.core;


import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import xxl.core.exception.UnrecognizedEntryException;


/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {
  @Serial
  private static final long serialVersionUID = 202308312359L;
  
    private int _rows;
    private int _columns;
    private boolean _changed;
    private List<User> _users = new ArrayList<>();
    private Matriz _cells;
    private CutBuffer _cut;
    
    public Spreadsheet(){

    }

    public Spreadsheet(int rows, int columns) {
      _rows = rows;
      _columns = columns;
      _cells = new MatrizBidimensional(rows, columns); // if its you want to use sparce matrix just create a class sparce matriz, implement matriz like in matriz bidimensional and change this argument to new SparceMatriz
      for(int i = 0; i < rows; i++){
        for(int j = 0; j < columns; j++){
          _cells.set(i, j, new Cells(i +1, j + 1));
        }
      }
    }
  /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change 
   * @param column the column of the cell to change
   * @param content the specification in a string format of the content to put
   *        in the specified cell.
   */


  public void insert(int row, int column, Content content){
    _changed = true;
    _cells.get(row - 1, column - 1).setContent(content);
  }

  public void insertContent(String gama, String content){
    try{
    Parser _parser =  new Parser(this);
    Content cont = _parser.parseContent(content);
    List<Cells> cell = showCellsByGama(gama);
    for(int i = 0; i < cell.size(); i++){
      Cells c = cell.get(i);
      insert(c.getRow(), c.getColumn() ,cont);
    }
    }catch(UnrecognizedEntryException e){
    }
  }
  /**
   * return a list of cell restricted to the gama that has received.
   *
   * @param gama a string that contains the first cell and the last cell for  
   *        the creation of a new gama       
   */
  public List<Cells> showCellsByGama(String gama)throws UnrecognizedEntryException{
    Range r =  createRange(gama);
    return r.getCells();
  }  

  public List<Cells> showCellsByValue(String value){
    List<Cells> cell = new ArrayList<Cells>();
    SearchValue sValue = new SearchValue(value);
    executeOperation(sValue);
    cell = sValue.returnCells();
    return cell;
  }

  public List<Cells> showCellsByFunction(String function){
    List<Cells> cell = new ArrayList<Cells>();
    SearchFunction sFunction = new SearchFunction(function);
    executeOperation(sFunction);
    cell = sFunction.returnCells();
    return cell;
  }

  public void addUser(User u){ //adds a new user
    _users.add(u);
  }

  /**
   * creates a new range from a string, spliting the cell in 4 parameters
   *
   * @param range a string that contains the first cell and the last cell for  
   *        the creation of a new range       
   */

  public Range createRange(String range) throws UnrecognizedEntryException  {
    String[] rangeCoordinates;
    int firstRow, firstColumn, lastRow, lastColumn;
    if (range.indexOf(':') != -1) {
      rangeCoordinates = range.split("[:;]");
      firstRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = Integer.parseInt(rangeCoordinates[1]);
      lastRow = Integer.parseInt(rangeCoordinates[2]);
      lastColumn = Integer.parseInt(rangeCoordinates[3]);
    } else {
      rangeCoordinates = range.split(";");
      firstRow = lastRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = lastColumn = Integer.parseInt(rangeCoordinates[1]);
    }
    if(firstRow <= 0 || firstColumn <= 0 || lastColumn > _columns  || lastRow > _rows ){
      throw new UnrecognizedEntryException("'" + range + "'");
    }  
    if(firstRow != lastRow && lastColumn != firstColumn  ){
      throw new UnrecognizedEntryException("'" + range + "'");
    } 
    return new Range (firstRow, firstColumn, lastRow, lastColumn, this);
  }

  public boolean hasChanged(){ //give the status change from a spreedsheat
    return _changed;
  }

  
  public Cells getCell(int r, int c){
    return _cells.get(r - 1, c - 1);
  }

  public void reset(){
    _changed = false;
  }

  public List<Cells> getCutBuffer(){
    return _cut.getCells();
  }

   /**
   * copy to a cutbuffer cells from a range
   *
   * @param range a string that contains the first cell and the last cell for  
   *        the creation of a new range       
   */

  public void copy(String range) throws UnrecognizedEntryException{
    Range r = createRange(range);
    boolean b = r.getBeginRow() == r.getEndRow(); // create a boolean to verify if its a row or column to be copy i f true its a column else its a row
    _cut = new CutBuffer(r.getCells(), b);
 
  }

  /**
   * clears the content of the cells defined by a range
   *
   * @param range a string that contains the first cell and the last cell for  
   *        the creation of a new range       
   */


  public void clear(String range) throws UnrecognizedEntryException{
    try{
      List<Cells> cell = showCellsByGama(range);
      for(int i = 0; i < cell.size(); i++){
        Cells c = cell.get(i);
        c.setContent(null);
      }
     }catch(UnrecognizedEntryException e){
    }   
  }

  /**
   * copy to a cutbuffer cells from a range and clears their content
   *
   * @param range a string that contains the first cell and the last cell for  
   *        the creation of a new range       
   */
  public void cut(String range) throws UnrecognizedEntryException {
    copy(range);
    clear(range);
  }

  /**
   * paste the content from cells in the cutbuffer to a range in the spreadsheet
   *
   * @param range a string that contains the first cell and the last cell for  
   *        the creation of a new range       
   */

  public void paste(String range) throws UnrecognizedEntryException{
    Range r = createRange(range);
    List<Cells> cutBuff = getCutBuffer();
    List<Cells> cells = r.getCells();
    
    if(cutBuff.size() != 0){
      if (cells.size() == cutBuff.size()){
        for(int i = 0; i < cells.size(); i++){
          cells.get(i).setContent(cutBuff.get(i).getContent());
        }
      }
      else if(cells.size() == 1){
        if(cutBuff.size() == 1){
          cells.get(0).setContent(cutBuff.get(0).getContent());
        }
        else{
          int l = 0;
          int p = cells.get(0).getRow();
          int o = cells.get(0).getColumn();
          if(cutBuff.get(0).getColumn() == cutBuff.get(1).getColumn()){ // equal column means its a row
            while(p <= _rows && l < cutBuff.size()){
              _cells.get(p - 1, o - 1).setContent(cutBuff.get(l).getContent());
              p++;
              l++;
            }
          }
          else{ // gonna copy a column
            while(o <= _columns && l < cutBuff.size()){
              _cells.get(p - 1, o - 1).setContent(cutBuff.get(l).getContent());
              o++;
              l++;
            }
          }
        }
      }
      else{
        throw new UnrecognizedEntryException("'" + range + "'");
      }
    }  
  }
  public void executeOperation(Visitor v) {
    for(int i = 0; i < _rows; i++){
      for(int j = 0; j < _columns; j++){
        _cells.get(i, j).accept(v);
      }
    }
  }
  public List<User> getUser(){
    return _users;
  }

}