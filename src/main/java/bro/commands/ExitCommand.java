package bro.commands;

import bro.Storage;
import bro.TaskManager;
import bro.Ui;

/**
 * Represents a command to cause the application to quit.
 */
public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes and shows a goodbye message to the Ui.
     *
     * @param storage Not used.
     * @param ui The Ui to display the goodbye message to.
     * @param taskManager Not used.
     * @return true after displaying goodbye message to the ui.
     */
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) {
        this.response = ui.showBye();
        return true;
    }
}
