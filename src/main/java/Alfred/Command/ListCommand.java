package Alfred.Command;

import Alfred.Storage.AlfredStorage;
import Alfred.UI.AlfredUserInterface;

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
        String out = "";
        out += "Sir, here are the things you need to do:\n";
        out += storage.listToString();
        ui.sandwichAndPrint(out);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
