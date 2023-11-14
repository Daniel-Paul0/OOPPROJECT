package xxl.app.main;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;

/**
 * Open menu.
 */
class DoRemoveSheets extends Command<Calculator> {

  DoRemoveSheets(Calculator receiver) {
    super(Label.NEW, receiver);
    addIntegerField("number");
  }
  
  @Override
  protected final void execute() throws CommandException {
    List <Spreadsheet> sp = receiver.getSpreadsheet();
    List <Spreadsheet> lspread;
    int folhas;
    for(int i = 0; i < sp.size(); i++){
      if(sp.get(i).getUser().size() > integerField("number")){
        lspread.add(sp.get(i));
        _receiver.removeSpread();
      }
    }
    _display.addLine(integerField("number"));
    for(int l = 0; l < lspread.size(); l++ ){
      int p = 0;
      p = lspread.get(l).getRow() + 1 * lspread.get(l).getColumn();
      _display.addLine(p);
    }
    _display.display();
  }
}