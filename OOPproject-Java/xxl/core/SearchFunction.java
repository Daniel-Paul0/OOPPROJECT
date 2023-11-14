package xxl.core;

import java.util.ArrayList;
import java.util.List;

public class SearchFunction implements Visitor {
  private List<Cells> _cells = new ArrayList<Cells>();
  private String _function;

  public SearchFunction(String function){
    _function = function;
  }
  public void visitCells(Cells cells){
    Content c = cells.getContent();
    if(c != null){
      if(c.toString().contains("=")){
        String args[] = c.toString().split("=");
        if(args[1].contains(_function)){
          _cells.add(cells);
        }
      }
    }
  }
  
  public List<Cells> returnCells(){
    return _cells;
  }
}
