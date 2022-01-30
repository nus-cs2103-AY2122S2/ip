package duke.command;

import duke.ui.TaskList;
import duke.ui.Ui;
import duke.ui.Storage;

/**
 * An extension from Command.
 * Deals with the listing of all tasks
 * from a given taskList.
 */
public class ListCommand extends Command {

    /**
     * Returns a string indicating the result
     * of listing all the tasks from a given
     * taskList.
     * Executes a method from taskList which prints
     * out the contents from the taskList with numeric
     * notation.
     * @param taskList a list of the current tasks
     * @param ui user interface
     * @param storage file storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.listOut();
    }

    /**
     * Returns false for non-Exit commands.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
