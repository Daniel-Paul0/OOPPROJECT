package xxl.app.edit;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Cells;
import xxl.core.Spreadsheet;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Delete command.
 */
class DoDelete extends Command<Spreadsheet> {

  DoDelete(Spreadsheet receiver) {
    super(Label.DELETE, receiver);
    addStringField("gama", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    if(stringField("gama").length() < 3){
            throw new InvalidCellRangeException(stringField("gama"));
    }
    try{
      _receiver.clear(stringField("gama"));
    }
    catch(UnrecognizedEntryException e){
      throw new InvalidCellRangeException(stringField("gama"));
    }
  }
}
