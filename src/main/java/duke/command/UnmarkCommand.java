package duke.command;

import duke.operations.TaskList;

/**
 * Represents a subclass of Command.
 */
public class UnmarkCommand extends Command {
    /**
     * A constructor for <code>UnmarkCommand</code>.
     *
     * @param num the index in the arraylist to be marked.
     */
    public UnmarkCommand(Integer num) {
        super(null, num);
    }

    /**
     * Unmarks the task in the arraylist.
     *
     * @param tasks the task to be marked in the arraylist.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.unmark(num);
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
