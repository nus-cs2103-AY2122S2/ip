package alfred.command;

import alfred.exceptions.InvalidDateTimeException;
import alfred.exceptions.InvalidInputException;
import alfred.storage.AlfredStorage;
import alfred.ui.AlfredUserInterface;

/**
 * Encapsulates the exit command.
 */
public class ExitCommand extends Command {

    /**
     * Calls the UI to print exit message to the console.
     *
     * @param ui      AlfredUserInterface object used as the UI interface for Alfred.
     * @param storage AlfredStorage object used to handle internal storage for Alfred.
     */
    @Override
    public void execute(AlfredUserInterface ui, AlfredStorage storage) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
