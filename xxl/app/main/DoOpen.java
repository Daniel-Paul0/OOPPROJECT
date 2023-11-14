package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import xxl.core.exception.UnavailableFileException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Open existing file.
 */
public class DoOpen extends Command<Calculator> {

  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
    addStringField("file",Message.openFile());
  }
  
  @Override
  protected final void execute() throws CommandException{
    if(_receiver.getSave() == true){
      Form form = new Form();
      if(form.confirm(Message.saveBeforeExit()) == true){
        new DoSave(_receiver).execute();
      }
    }
    try{
      _receiver.load(stringField("file")); //cria na calculadora um load
    }
    catch (IOException | UnavailableFileException | ClassNotFoundException e){
      throw new FileOpenFailedException(e);
    }
  }
   
}
