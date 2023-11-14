package xxl.app.edit;

import java.util.List;
import xxl.core.exception.UnrecognizedEntryException;

import java.util.ArrayList;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Cells;
import xxl.core.Content;
import xxl.core.Spreadsheet;
import pt.tecnico.uilib.forms.Form;
import xxl.app.exception.InvalidCellRangeException;


/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

  DoShow(Spreadsheet receiver) {
    super(Label.SHOW, receiver);
    addStringField("gama", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    if(stringField("gama").length() < 3){
            throw new InvalidCellRangeException(stringField("gama"));
    }
    try{
      List<Cells> cells = _receiver.showCellsByGama(stringField("gama"));
      for(Cells cell : cells){
        _display.addLine(cell.toString());
      _display.display();
      }
    }
    catch(UnrecognizedEntryException e){
      throw new InvalidCellRangeException(stringField("gama"));
    }
  }
}
