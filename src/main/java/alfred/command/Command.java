package alfred.command;


import alfred.exceptions.AlfredException;
import alfred.storage.AlfredStorage;
import alfred.ui.AlfredUserInterface;

/**
 * This is an abstract class encapsulating all valid commands
 * read by Alfred.
 */
public abstract class Command {
    public abstract void execute(AlfredUserInterface ui, AlfredStorage storage) throws
            AlfredException;

    public abstract boolean isExit();

    public abstract String response(AlfredUserInterface ui, AlfredStorage storage) throws AlfredException;
}
