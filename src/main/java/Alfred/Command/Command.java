package Alfred.Command;


import Alfred.Exceptions.AlfredException;
import Alfred.Exceptions.InvalidInputException;
import Alfred.Storage.AlfredStorage;
import Alfred.UI.AlfredUserInterface;

public abstract class Command {
  public abstract void execute(AlfredUserInterface ui, AlfredStorage storage) throws
      AlfredException;

  public abstract boolean isExit();
}
