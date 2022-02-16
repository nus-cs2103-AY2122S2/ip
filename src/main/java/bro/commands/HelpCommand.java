package bro.commands;

import bro.Storage;
import bro.TaskManager;
import bro.Ui;

/**
 * Represents a command to find all tasks containing the specified keyword.
 */
public class HelpCommand extends Command {

    public HelpCommand() {
    }

    /**
     * Executes and prints out the help menu.
     *
     * @param storage Not used.
     * @param ui The Ui to display search results to.
     * @param taskManager Not used.
     * @return True after printing help.
     */
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) {
        this.response = ui.showHelpMenu();
        return true;
    }
}
