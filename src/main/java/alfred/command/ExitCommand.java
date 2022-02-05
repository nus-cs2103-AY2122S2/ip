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

    /**
     * Calls the UI and returns desired exit message.
     *
     * @param ui      AlfredUserInterface object used as the UI interface for Alfred.
     * @param storage AlfredStorage object used to handle internal storage for Alfred.
     * @return String output meant for user.
     */
    @Override
    public String response(AlfredUserInterface ui, AlfredStorage storage) {
        return ui.getByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
