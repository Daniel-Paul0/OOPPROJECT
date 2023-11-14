package xxl.core;



	public abstract class Function extends Content {
  	private String _name;
    
  public Function(String s){
  	_name = s;
	}

/**
 * Function - gives the methods to sub classes
 */

	protected abstract Literal compute();

  public String asString(){
		return value().asString();
  }

  public int asInt(){
		return compute().value().asInt();
  }
    
  public Literal value(){
    return compute();
  }

  public String getName() {
    return _name;
  }

}
