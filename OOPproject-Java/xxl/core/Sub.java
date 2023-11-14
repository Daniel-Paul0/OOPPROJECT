package xxl.core;


public class Sub extends BinaryFunction{
    
  public Sub(Content c1, Content c2){
    super("SUB", c1, c2);
  }

/**
 * Function Subtraction
 */

  @Override
  protected Literal compute(){
    if(getc1().value() == null || getc2().value() == null){
      return null;
    }
    if(getc1().value().asInt() == -123748258|| getc2().value().asInt() == -123748258 ){ // -123748258 code for null
      return null;
    }
    LiteralInteger result = new LiteralInteger(getc1().asInt() - getc2().asInt());
    return result;
  }
}