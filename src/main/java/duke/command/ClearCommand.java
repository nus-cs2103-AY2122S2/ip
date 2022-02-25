package duke.command;

import duke.ui.TaskList;
import duke.ui.Ui;
import duke.ui.Storage;

/**
 * An extension from the Command class.
 * Deals with the clearing of tasks from the tasklist
 */
public class ClearCommand extends Command {

    /**
     * Returns a string indicating that the list has been emptied.
     *
     * @param taskList a list of the current tasks
     * @param ui       user interface
     * @param storage  file storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.clear();
        return "INITIATING EXTERMINATION OF TASKS...\nTASKLIST IS NOW EMPTY.";
    }

    /**
     * Returns false for non-exit commands
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
