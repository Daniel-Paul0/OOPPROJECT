package xxl.core;

import java.util.List;

public class Product extends IntervalFunction{

  public Product(Range range){
    super("PRODUCT", range);
  }
    
/**
 * Function Product
 */

  @Override
  protected Literal compute(){
    List<Cells> cell = super.getCellsFromRange();
    int product = 1;

    for(int i = 0; i < cell.size(); i++){
      Content c = cell.get(i).getContent();
      if(c == null){
        return null;
      }
      else if(c.value().asInt() == -123748258){
        return null;
      }
      else{
        product *= c.value().asInt();
      }
    }
    return new LiteralInteger(product);
  }
}
