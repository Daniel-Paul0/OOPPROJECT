package xxl.core;

import java.util.List;

public class Min extends IntervalFunction{

  public Min(Range range){
    super("MIN", range);
  }
    
/**
 * Function Product
 */

  @Override
  protected Literal compute(){
    List<Cells> cell = super.getCellsFromRange();
    int min = 0;

    for(int i = 0; i < cell.size(); i++){
      Content c = cell.get(i).getContent();
      if(c == null){
        return null;
      }
      else if(c.value().asInt() == -123748258){
        continue;
      }
      else{
        if(min > c.value().asInt()){
          min = c.value().asInt();
        }
      }
    }
    return new LiteralInteger(min);
  }
}