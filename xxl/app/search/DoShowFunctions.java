package xxl.app.search;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Cells;
import xxl.core.Spreadsheet;

/**
 * Command for searching function names.
 */

class DoShowFunctions extends Command<Spreadsheet> {

  DoShowFunctions(Spreadsheet receiver) {
    super(Label.SEARCH_FUNCTIONS, receiver);
    addStringField("function", Message.searchFunction());
  }

  @Override
  protected final void execute() {
    List<Cells> cells = _receiver.showCellsByFunction(stringField("function"));
    for(Cells cell : cells){
      _display.addLine(cell.toString());
    _display.display();
    }  
  }
}
