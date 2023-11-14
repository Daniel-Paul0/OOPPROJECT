package xxl.core;

import java.util.List;

public class Average extends IntervalFunction{

  public Average(Range range){
    super("AVERAGE", range);
  }
    
/**
 * Function Average
 */

  @Override
  protected Literal compute(){
    List<Cells> cell = super.getCellsFromRange();
    int average = 0;

    for(int i = 0; i < cell.size(); i++){
      Content c = cell.get(i).getContent();
      if(c == null){
        return null;
      }
      else if(c.value().asInt() == -123748258){
        return null;
      }
      else{
        average += c.value().asInt();
      }
    }
    return new LiteralInteger(average / cell.size());
  }
}
