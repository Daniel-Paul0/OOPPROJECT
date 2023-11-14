package xxl.app.edit;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Cells;
import xxl.core.Spreadsheet;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Cut command.
 */
class DoCut extends Command<Spreadsheet> {

  DoCut(Spreadsheet receiver) {
    super(Label.CUT, receiver);
    addStringField("gama", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    if(stringField("gama").length() < 3){
      throw new InvalidCellRangeException(stringField("gama"));
    }
    try{
      _receiver.cut(stringField("gama"));
    }
    catch(UnrecognizedEntryException e){
      throw new InvalidCellRangeException(stringField("gama"));
    }
  }
}
