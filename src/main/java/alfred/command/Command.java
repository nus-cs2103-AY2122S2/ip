package alfred.command;


import alfred.exceptions.AlfredException;
import alfred.storage.AlfredStorage;
import alfred.ui.AlfredUserInterface;

public abstract class Command {
    public abstract void execute(AlfredUserInterface ui, AlfredStorage storage) throws
        AlfredException;

    public abstract boolean isExit();
}
