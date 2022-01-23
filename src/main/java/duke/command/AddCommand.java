package duke.command;

import duke.task.Task;
import duke.operations.TaskList;

/**
 * Represents a subclass of Command.
 */
public class AddCommand extends Command {
    /**
     * A constructor for <code>AddCommand</code>.
     *
     * @param task the task to be instantiated with.
     */
    public AddCommand(Task task) {
        super(task,null);
    }

    /**
     * Adds the task into the arraylist.
     *
     * @param tasks the task to be added into the arraylist.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.addToList(super.task);
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
