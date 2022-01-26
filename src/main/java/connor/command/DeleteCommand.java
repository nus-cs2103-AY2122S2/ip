package connor.command;

import connor.task.TaskList;

/**
 * Represents a Delete {@code Command}.
 */
public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the {@code Task} in the task list with the given index.
     */
    @Override
    public void activate() {
        TaskList.deleteTask(index);
    }
}
