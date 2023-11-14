package xxl.core;

import java.util.ArrayList;
import java.util.List;

public class SearchValue implements Visitor {
  private List<Cells> _cells = new ArrayList<Cells>();
  private String _value;

  public SearchValue(String val){
    _value = val;
  }
  public void visitCells(Cells cells){
    int valI = 0;
    if(_value.charAt(0) != '='){ //if starts with a = its a function and not a value
      try{
        if(_value.charAt(0) != '\''){
        valI = Integer.parseInt(_value);
      }
      }catch (NumberFormatException e){ // if the user try to search for a string without starting with '
        return;
      }
      Content c = cells.getContent();
        if(c != null){
          if(c.asInt() == valI){
            _cells.add(cells);
          }
          else if(c.asString().equals(_value)){
            _cells.add(cells);
          }
      }
    }
  }
  
  public List<Cells> returnCells(){
    return _cells;
  }
}
