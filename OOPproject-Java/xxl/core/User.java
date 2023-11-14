package xxl.core;


import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;
import xxl.core.exception.UnrecognizedEntryException;


public class User implements Serializable{
  private String _name;
  private List<Spreadsheet> _sheets = new ArrayList<Spreadsheet>();

  User(String name) throws UnrecognizedEntryException {
  	if(name.length() == 0){
      throw new UnrecognizedEntryException(name);
    }else{
      _name = name;
    }
  }

/**
 * User - a profile for the user of the application
 */

  public int hashCode(){
    return _name.hashCode();
  }

  void add(Spreadsheet sheet){
    _sheets.add(sheet);
  }

  public String getName(){
    return _name;
  }

  @Override
  public boolean equals(Object o){
    if(o instanceof User){
      User outro = (User) o;
      return _name.equals(outro.getName());
    }else{
      return false;
    }
  }

  public List<Spreadsheet> getSpreadSheets() { /*added to use in "teste pratico" */
    return Collections.unmodifiableList(_sheets);
  }
}
