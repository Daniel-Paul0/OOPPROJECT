package xxl.core;


import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


public class Range implements Serializable{
  private int _beginRow;
  private int _endRow;
  private int _beginColumn;
  private int _endColumn;
  private Spreadsheet _spreadsheet;  

  Range(int beginRow, int beginColumn, int endRow, int endColumn, Spreadsheet sheet){
    _beginRow = beginRow;
    _beginColumn = beginColumn;
    _endRow = endRow;
    _endColumn = endColumn;
    _spreadsheet = sheet;
  }

/**
 * Range - save the first and last column/row from a group of cells 
 */

  public int getBeginRow(){
    return _beginRow;
  }

  public int getBeginColumn(){
    return _beginColumn;
  }

  public int getEndRow(){
    return _endRow;
  }

  public int getEndColumn(){
    return _endColumn;
  }

  public List<Cells> getCells(){ //returns a list of cell that were in a range given
    List<Cells> lc = new ArrayList<>();
    if(_beginColumn == _endColumn && _beginRow == _endRow){
      lc.add(_spreadsheet.getCell(_beginRow, _endColumn));
    }
    else if(_beginColumn == _endColumn){
      int l = _beginColumn;
      for (int i = _beginRow ; i <= _endRow; i++){
        lc.add(_spreadsheet.getCell(i, l));
      }
    }
    else if (_beginRow == _endRow){
      int i = _beginRow;
      for (int l = _beginColumn ; l <= _endColumn; l++){
        lc.add(_spreadsheet.getCell(i, l));
      }
    }
    return lc;
  }
}   


