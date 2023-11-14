package xxl.app.edit;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Cells;
import xxl.core.Spreadsheet;


/**
 * Show cut buffer command.
 */
class DoShowCutBuffer extends Command<Spreadsheet> {

  DoShowCutBuffer(Spreadsheet receiver) {
    super(Label.SHOW_CUT_BUFFER, receiver);
  }
  
  @Override
  protected final void execute() {
    List<Cells> cells = _receiver.getCutBuffer();
      for(Cells cell : cells){
        _display.addLine(cell.toString());
      _display.display();
      }
  }
}
