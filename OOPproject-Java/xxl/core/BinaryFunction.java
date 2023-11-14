package xxl.core;

public abstract class BinaryFunction extends Function{
  private Content _c1;
  private Content _c2;

  public BinaryFunction(String s, Content c1, Content c2){ //receive two contents and the function name
    super(s);
    _c1 = c1;
    _c2 = c2;
  }

/**
 * methods for all binary functions
 */

  public String toString(){ // toString in BinaryFunction form
    String n = super.getName();
    String c1S = obterValorAposIgual(_c1.toString());
    String c2S = obterValorAposIgual(_c2.toString());

    if(compute() == null){
      return("#VALUE" + "=" + n + "(" + c1S + "," + c2S + ")");
    }
        
    return(compute().asInt() + "=" + n + "(" + c1S + "," + c2S + ")");
  }

  protected abstract Literal compute(); 

  public Content getc1(){
    return _c1;
  }

  public Content getc2(){
    return _c2;
  }

  public String obterValorAposIgual(String input) {
    int indiceIgual = input.indexOf('=');
    if(indiceIgual != -1) {
      if(indiceIgual < input.length() - 1) {
        return input.substring(indiceIgual + 1);
      }else{ 
        return "";
      }
    }else{
      return input;
    }
  }
}