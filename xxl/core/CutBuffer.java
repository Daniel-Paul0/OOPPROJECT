package xxl.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CutBuffer implements Serializable{
  Cells _cell[][];

/**
 * CutBuffer - retains the content from all the copyed cells
 */

  public CutBuffer(List<Cells> cells, boolean b){
    List<Cells> cell = cells;
    if(!b){ //if b its false its a row else its a column
      _cell = new Cells[cell.size()][1];
      for(int i = 0; i < cell.size(); i++){
        _cell[i][0] = new Cells(i + 1, 1);
        _cell[i][0].setContent(cell.get(i).getContent());
      }
    }
    else {
      _cell = new Cells[1][cell.size()];
      for(int i = 0; i < cell.size(); i++){
        _cell[0][i] = new Cells(1, i + 1);
        _cell[0][i].setContent(cell.get(i).getContent());
      }
    }
  }

  List<Cells> getCells(){
    List<Cells> cell = new ArrayList<Cells>();
    for(int i = 0; i < _cell.length; i++){
      for(int l = 0; l < _cell[0].length; l++){
        cell.add(_cell[i][l]);
      }
    }
    return cell;
  }
}