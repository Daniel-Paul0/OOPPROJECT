package xxl.core;


public class LiteralInteger extends Literal{ 
  private int _value;

  public LiteralInteger(int val){
    _value = val;
  }
/**
 * LiteralInteger - a form of content - interger
 */

  @Override
  public String toString(){ 
	  return "" + _value;
  }

  @Override
  public String asString(){
    return _value + "";
  }

  @Override
  public int asInt(){
    return _value;
  }

  @Override
  Literal value() {
    return this;
  }
}
