package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a List command to display all the list
 */
public class ListCommand extends Command {

    /**
     * Calls and formats the string out put of list.
     *
     * @param tasks   the entire TaskList.
     * @param ui      the ui interface and messages.
     * @param storage the storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayTaskList(tasks, "Here are the tasks in your list:\n");
    }
}
