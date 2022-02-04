package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;

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
    public String exec(TaskList taskList, Storage storage) {
        String message = taskList.addTask(this.task);
        storage.saveAddedTask(this.task);
        return message;
    }

    @Override
    public boolean shouldAbort() {
        return false;
    }
}
