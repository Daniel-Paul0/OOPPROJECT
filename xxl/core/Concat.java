package xxl.core;

import java.util.List;

public class Concat extends IntervalFunction{
    
  public Concat(Range range){
    super("CONCAT", range);
  }

/**
 * Function Concat - gives the strings in the range all together 
 */

  protected Literal compute(){
    List<Cells> cell = super.getCellsFromRange();
    StringBuilder string = new StringBuilder();
    for(int i = 0; i < cell.size(); i++){
      Content c = cell.get(i).getContent();
      if(c == null || c.value().asString().charAt(0) != '\''){
        continue;
      }
      else{
        if(c.toString().length() > 1){
          string.append(c.asString().substring(1));
        }
        else{
          continue;
        }
      }
    }
    return new LiteralString("'" + string.toString());
  }
}
