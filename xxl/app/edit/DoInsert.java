package xxl.app.edit;

import java.util.ArrayList;
import java.util.List;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Cells;
import xxl.core.Content;
import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Class for inserting data.
 */
class DoInsert extends Command<Spreadsheet> {

  DoInsert(Spreadsheet receiver) {
    super(Label.INSERT, receiver);
    addStringField("gama", Message.address());
    addStringField("content", Message.contents());
  }
  
  @Override
  protected final void execute() throws CommandException {
    _receiver.insertContent(stringField("gama"), stringField("content"));
  }
}