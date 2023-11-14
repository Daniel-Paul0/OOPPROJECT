package xxl.core;


public class Reference extends Content{
  private int _row;
  private int _column;
  private Spreadsheet _spreadsheet;
  private Cells _cl;

  public Reference(int row, int colunm, Spreadsheet spreadsheet){   
    _row = row;
    _column = colunm;
    _spreadsheet = spreadsheet;
  }

/**
 * Reference - keeps the row and the column of other cell
 */

  @Override
  public String toString(){                   //reference toStrinf Format
    if(this.value() == null){                    // if cell does not a content return this way
      return "#VALUE ="  + _row + ";" + _column; 
    }
    return "" + value().toString() + "=" + _row + ";" + _column; //the way of return if has a value
  }

  @Override
  Literal value(){
    _cl = _spreadsheet.getCell(_row, _column);
    Content content = _cl.getContent(); 
    if(content == null){
      return null;
    }
    return content.value();
  }

  public String asString(){
    if(this.value() == null){                    
      return "#VALUE"; 
    }
    return value().toString(); 
  }
}