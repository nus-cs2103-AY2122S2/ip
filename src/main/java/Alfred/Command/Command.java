package Alfred.Command;


import Alfred.Exceptions.AlfredException;
import Alfred.Exceptions.InvalidInputException;
import Alfred.Storage.AlfredStorage;
import Alfred.UI.AlfredUserInterface;

/**
 * This is an abstract class encapsulating all valid commands
 * read by Alfred.
 */
public abstract class Command {
    public abstract void execute(AlfredUserInterface ui, AlfredStorage storage) throws
            AlfredException;

    public abstract boolean isExit();
}
