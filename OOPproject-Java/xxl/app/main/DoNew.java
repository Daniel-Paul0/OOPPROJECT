package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;

/**
 * Open a new file.
 */
public class DoNew extends Command<Calculator> {

  private int _columns;
  private int _lines;
  
  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    addIntegerField("lines",Message.lines());
    addIntegerField("colunm",Message.columns());
  }
  
  @Override
  protected final void execute() throws CommandException {
    if(_receiver.getSpreadsheet() != null && _receiver.getSave() == true){
      Form form = new Form();
      if(form.confirm(Message.saveBeforeExit())){
        new DoSave(_receiver).execute();
      }
    }
    _receiver.createNewSpreadsheet(integerField("lines"), integerField("colunm")); 
  }
}
