package xxl.core;

import java.util.List;

public class Coalesce extends IntervalFunction{
    
  public Coalesce(Range range){
    super("COALESCE", range);
  }
    
/**
 * Function Coalesce - gives the first string in a range
 */

  protected Literal compute(){
    List<Cells> cell = super.getCellsFromRange();
    String string = "";
    for(int i = 0; i < cell.size(); i++){
      Content c = cell.get(i).getContent();
      if(c == null || c.value().asString().charAt(0) != '\''){
        continue;
      }
      else if((c.toString().length() > 1)){
        string = c.asString(); 
        break;
      }
      else{
        break;
      }
    }                
    return new LiteralString(string);       
  }
}