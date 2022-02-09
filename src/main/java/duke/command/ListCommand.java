package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command for listing all the tasks.
 */
public class ListCommand implements Command {

    /**
     * Constructor for a ListCommand object.
     */
    public ListCommand() {}

    /**
     * Executes the command.
     *
     * @param taskList the list of the tasks a user has.
     * @param ui an instance of a user interface.
     * @param storage a storage used to save the user's tasks.
     * @return a boolean indicating whether it is an exit command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.listTasks();
    }
}
