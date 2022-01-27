package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that lists all tasks in the list.
 */
public class ListCommand extends Command {

    /**
     * Instructs UI object to display all tasks in the list in a numbered list.
     *
     * @param taskList The list of tasks.
     * @param ui The UI object r.
     * @param storage The Storage object responsible for retrieving/storing the list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayAllTasks(taskList.getTasks());
    }
}
