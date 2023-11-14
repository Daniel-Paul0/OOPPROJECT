package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.exception.MissingFileAssociationException;
import xxl.app.exception.FileOpenFailedException;
import xxl.app.exception.InvalidCellRangeException;
import java.io.IOException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<Calculator> {


  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
    if(_receiver.getFileName() == null){
      addStringField("file",Message.newSaveAs());//
    }
  }
  
  @Override
  protected final void execute() throws CommandException{
    if(stringField("file").length() < 5){
      throw new InvalidCellRangeException(stringField("file"));
    }

      try {
        _receiver.save();

      } catch ( MissingFileAssociationException mfae ) {
        try {
          _receiver.saveAs(stringField("file"));

        } catch( MissingFileAssociationException | IOException e ) {
          throw new FileOpenFailedException(e);
        }
        
      } catch ( IOException ioe ) {
        throw new FileOpenFailedException(ioe);  
      }
    }
}

    // FIXME implement command and create a local Form
