package xxl.core;

import java.io.Serializable;

public class Cells implements Serializable{
  private final int _row;
  private final int _column;
  private Content _content;

  /**
 * Class representing a cell.
 */

  public Cells(int r, int c){
    _row = r;
    _column = c;
  }


  Literal value(){
    return _content.value();
  }

  public String toString(){ // format of toString of a cell
    if(_content == null){
      return "" + _row + ";" + _column + "|";
    }
    return "" + _row + ";" + _column + "|" + _content.toString();
  }

  public int getRow(){
    return _row;
  }

  public int getColumn(){
    return _column;
  }

  Content getContent(){
    return _content;
  }

  public void setContent(Content cont){ //set content from a received content
    _content =  cont;
  }

  public void accept(Visitor v) {
    v.visitCells(this);
  }
}
