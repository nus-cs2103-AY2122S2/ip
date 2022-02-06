package alfred.command;

import alfred.Alfred;
import alfred.storage.AlfredStorage;
import alfred.ui.AlfredUserInterface;

/**
 * Encapsulates the list command.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by iterating through all tasks
     * in the internal data state and printing them to console.
     *
     * @param ui      AlfredUserInterface object used as the UI interface for Alfred.
     * @param storage AlfredStorage object used to handle internal storage for Alfred.
     */
    @Override
    public void execute(AlfredUserInterface ui, AlfredStorage storage) {
        String out = this.response(ui, storage);
        ui.sandwichAndPrint(out);
    }

    /**
     * Executes the list command by iterating through all tasks
     * in the internal data state and printing them to console.
     *
     * @param ui      AlfredUserInterface object used as the UI interface for Alfred.
     * @param storage AlfredStorage object used to handle internal storage for Alfred.
     * @return String output meant for user.
     */
    @Override
    public String response(AlfredUserInterface ui, AlfredStorage storage) {

        // get tasks from storage object
        String listDescription = storage.listToString();
        if (listDescription.length() == 0) {
            return "Sir, you have no tasks.";
        }

        // construct response
        String out = "";
        out += "Sir, here are the things you need to do:\n";
        out += listDescription;
        return out;
    }

    /**
     * Returns True if command is an Exit Command.
     *
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
