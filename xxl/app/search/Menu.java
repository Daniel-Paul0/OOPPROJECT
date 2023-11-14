package xxl.app.search;

import xxl.core.Spreadsheet;
import pt.tecnico.uilib.menus.Command;

/**
 * Menu builder for search operations.
 */
public class Menu extends pt.tecnico.uilib.menus.Menu {

  public Menu(Spreadsheet receiver) {
    super(Label.TITLE, //
          new DoShowValues(receiver), //
          new DoShowFunctions(receiver) //
    );
  }
}
