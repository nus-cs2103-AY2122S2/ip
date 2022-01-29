package duke.command;

import duke.operations.TaskList;
import duke.task.Task;

/**
 * Represents a subclass of Command.
 */
public class DeleteCommand extends Command {
    /**
     * A constructor for <code>DeleteCommand</code>.
     *
     * @param task the task to be instantiated with.
     */
    public DeleteCommand(Task task) {
        super(task, null, null);
    }

    /**
     * Deletes the task into the arraylist.
     *
     * @param tasks the task to be deleted from the arraylist.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.deleteFromList(super.task);
    }

    /**
     * Checks whether it is the exit command.
     *
     * @return if false, continue receiving input, else terminate.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
