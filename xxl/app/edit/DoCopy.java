package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Copy command.
 */
class DoCopy extends Command<Spreadsheet> {

  DoCopy(Spreadsheet receiver) {
    super(Label.COPY, receiver);
    addStringField("gama", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    if(stringField("gama").length() < 3){
            throw new InvalidCellRangeException(stringField("gama"));
    }
    try{
      _receiver.copy(stringField("gama"));
    }
    catch(UnrecognizedEntryException e){
      throw new InvalidCellRangeException(stringField("gama"));
    }
  }
}
