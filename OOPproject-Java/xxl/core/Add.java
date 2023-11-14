package xxl.core;


public class Add extends BinaryFunction{
    
  public Add(Content c1, Content c2){
      super("ADD", c1, c2);
  }
  
/**
 * Function Add
 */

  @Override
  protected Literal compute(){
    if(getc1().value() == null || getc2().value() == null){
      return null;
    }
    if(getc1().value().asInt() == -123748258|| getc2().value().asInt() == -123748258 ){ // -123748258 code for null
      return null;
    }
    LiteralInteger result = new LiteralInteger(getc1().value().asInt() + getc2().value().asInt());
    return result;
  }
}
