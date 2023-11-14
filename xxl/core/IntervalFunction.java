package xxl.core;

import java.util.List;


public abstract class IntervalFunction extends Function{
  private Range _range;

  public IntervalFunction(String s, Range range){
    super(s);
    _range = range;   
  }

/**
 * Inverval Function - All the functions that work with ranges
 */

  public String toString(){ // toString for Intervalfunction
    String n = super.getName();
        
    if(super.getName().contains("AV") || super.getName().contains("PR") ){
      if(compute() == null){
        return("#VALUE" + "=" + n + "(" + _range.getBeginRow()  + ";" + _range.getBeginColumn() +":" + _range.getEndRow() + ";" + _range.getEndColumn() + ")");
      }
      return(compute().asInt() + "=" + n + "(" + _range.getBeginRow()  + ";" + _range.getBeginColumn() +":" + _range.getEndRow() + ";" + _range.getEndColumn() + ")");
    }
    else{ 
      return(compute().toString() + "=" + n + "(" + _range.getBeginRow()  + ";" + _range.getBeginColumn() +":" + _range.getEndRow() + ";" + _range.getEndColumn() + ")");   
    }
  }

  protected abstract Literal compute();

  public List<Cells> getCellsFromRange(){
    return _range.getCells();
  }
}