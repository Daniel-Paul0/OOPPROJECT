package xxl.app.search;

import java.util.List;
import pt.tecnico.uilib.menus.Command;
import xxl.core.Cells;
import xxl.core.Spreadsheet;

/**
 * Command for searching content values.
 */
class DoShowValues extends Command<Spreadsheet> {

  DoShowValues(Spreadsheet receiver) {
    super(Label.SEARCH_VALUES, receiver);
    addStringField("value", Message.searchValue());
  }
  
  @Override
  protected final void execute() {
    List<Cells> cells = _receiver.showCellsByValue(stringField("value"));
    for(Cells cell : cells){
      _display.addLine(cell.toString());
    _display.display();
    }  
  }
}
