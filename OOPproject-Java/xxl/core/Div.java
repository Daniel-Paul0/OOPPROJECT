package xxl.core;


public class Div extends BinaryFunction{
    
  public Div(Content c1, Content c2){
    super("DIV", c1, c2);
  }

/**
 * Function Division
 */

  @Override
  protected Literal compute(){
    if(getc1().value() == null || getc2().value() == null){
      return null;
    }
    if(getc1().value().asInt() == -123748258|| getc2().value().asInt() == -123748258 ){ // -123748258 code for null
      return null;
    }
    LiteralInteger result = new LiteralInteger(getc1().asInt() / getc2().asInt());
    return result;
  }
}