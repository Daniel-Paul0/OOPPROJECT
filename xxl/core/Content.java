
package xxl.core;

import java.io.Serializable;

public abstract class Content implements Serializable{

/**
 * Super Class Content - gives the methods to all sub classes
 */

  @Override
  public abstract String toString(); //receive a toSting from a subclass
    
  abstract Literal value();  //receive a value from a subclass

    

  public String asString(){
    return value().asString();
  }

  public int asInt(){
    return value().asInt();
  }
}