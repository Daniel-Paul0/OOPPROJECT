package xxl.core;


import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;


/**
 * Class representing a spreadsheet application.
 */

public class Calculator {
  /** The current spreadsheet. */
  private Spreadsheet _spreadsheet;
  private String _name;
  private List <User> _users = new ArrayList<>();
  private User _activeUser;
  private List <Spreadsheet> _spreads = new ArrayList<>();
  
  
  /**
   * Return the current spreadsheet.
   *
   * @returns the current spreadsheet of this application. This reference can be null.
   */

  public final Spreadsheet getSpread() {
    return _spreadsheet;
  }

  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    if(_name == null)
      throw new MissingFileAssociationException();

    saveAs(_name);
  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    try(FileOutputStream fileOut = new FileOutputStream(filename); ObjectOutputStream oos = new ObjectOutputStream(fileOut)) {
      oos.writeObject(_spreadsheet);
      _name = filename;
      _spreadsheet.reset();
    } catch ( FileNotFoundException fnfe ) {
      throw new MissingFileAssociationException();
    } 
  }

  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   */
  public void load(String filename) throws IOException, ClassNotFoundException, FileNotFoundException, UnavailableFileException {
    try(FileInputStream fileIn = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fileIn)) {
      _spreadsheet = (Spreadsheet) ois.readObject();
      _name = filename;
    }
  }


  /**
   * Read text input file and create domain entities.
   *
   * @param filename name of the text input file
   * @throws ImportFileException
   */
  public void importFile(String filename) throws ImportFileException{
    try {
      Parser _parser =  new Parser();
      _spreadsheet  = _parser.parseFile(filename);
    } catch (IOException | UnrecognizedEntryException  e) {
      throw new ImportFileException(filename,e);
    }
  } 
  
   /**
   * return if the files was save or if needs to be safe
   */

  public boolean getSave(){
    if(_spreadsheet != null){
    return _spreadsheet.hasChanged();
    }
    else return false;
  }

  public String getFileName(){
    return _name;
  }

  public void setFileName(String name) {
    _name = name;
  }

  public void createNewSpreadsheet(int rows, int columns){
    _spreadsheet = new Spreadsheet(rows, columns);
  }

   /**
   * creates a new user form a given name
   * @param name name of the new user
   * @throws UnrecognizedEntryException if the name is not valid
   */

  public boolean createUser(String name) throws UnrecognizedEntryException {
    if(name == null){
      throw new UnrecognizedEntryException(name);
    }
      User u = new User(name);
      _spreadsheet.addUser(u);
      u.add(_spreadsheet);
      return _users.add(u);
  }

    /**
   * 
   * @param name name of the new user
   * @throws UnrecognizedEntryException if the name is not valid or does not exist a user with name given
   */
  public void setActiveUser(String name) throws UnrecognizedEntryException{
    if(name == null){
      throw new UnrecognizedEntryException(name);
    }
    for(User u : _users){
      if(u.getName().equals(_activeUser.getClass())){
        _activeUser = u;
        return;
      }
      else{
        throw new UnrecognizedEntryException(name);
      }
    }
  }

  public User getActiveUser(){
    return _activeUser;
  }

  public List<Spreadsheet> getSpreadsheet(){
    return _spreads;
  }

}


