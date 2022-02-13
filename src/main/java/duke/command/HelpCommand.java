package duke.command;

import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

/**
 * The functionality for 'help' input
 */
public class HelpCommand extends Command {

    /**
     * Returns a string "Help has arrived!"
     *
     * @param taskList a list of the current tasks taskList
     * @param ui       user interface
     * @param storage  file storage
     * @return "Help has arrived"
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "Help has arrived!";
    }

    /**
     * Returns false for non-Exit Commands
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
