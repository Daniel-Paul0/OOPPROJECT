package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Paste command.
 */
class DoPaste extends Command<Spreadsheet> {

  DoPaste(Spreadsheet receiver) {
    super(Label.PASTE, receiver);
    addStringField("gama", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    
    try{
      _receiver.paste(stringField("gama"));
    }
    catch(UnrecognizedEntryException e){
    }
  }
}
