package xxl.app.main;

import xxl.core.Calculator;
import pt.tecnico.uilib.menus.Command;

/**
 * Menu builder for managers.
 */
public class Menu extends pt.tecnico.uilib.menus.Menu {

  public Menu(Calculator receiver) {
    super(Label.TITLE, //
          new DoNew(receiver), //
          new DoOpen(receiver), //
          new DoSave(receiver), //
          new DoOpenEditMenu(receiver), //
          new DoOpenSearchMenu(receiver), //
          new DoRemoveSheets(receiver)
          );
  }
}
