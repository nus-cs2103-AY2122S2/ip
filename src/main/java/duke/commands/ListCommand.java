package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;


/**
 * Represents a command to show all tasks in the TaskManager.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    /**
     * Executes and prints the list of tasks currently in the TaskManager.
     * Returns true after displaying the task list to the Ui.
     *
     * @param storage Not used.
     * @param ui The Ui to display the task list to.
     * @param taskManager The TaskManager that contains the tasks to be displayed.
     * @return true after the list is displayed.
     */
    public String execute(Storage storage, Ui ui, TaskManager taskManager) {
        return ui.showList(taskManager);
    }
}
