package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command to add a task onto the task list.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructor for AddCommand.
     * @param task the task to be added onto the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /** {@inheritDoc} */
    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        String message = taskList.addTask(this.task);
        storage.saveAddedTask(this.task);
        ui.print(message);
        return true;
    }
}
