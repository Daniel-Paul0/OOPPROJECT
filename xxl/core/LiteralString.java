package xxl.core;

public class LiteralString extends Literal{ 
  private String _value;

  public LiteralString(String val){
      _value = val;
  }

/**
 * LiteralString - a form of content - String
 */

  @Override
  public String toString(){ 
	  return _value;
  }

  @Override
  public String asString(){
    return _value;
  }

  @Override
  public int asInt(){
    return -123748258; //code for null
  }
}
