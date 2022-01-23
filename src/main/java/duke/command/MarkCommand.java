package duke.command;

import duke.operations.TaskList;

/**
 * Represents a subclass of Command.
 */
public class MarkCommand extends Command {
    /**
     * A constructor for <code>MarkCommand</code>.
     *
     * @param num the index in the arraylist to be marked.
     */
    public MarkCommand(Integer num) {
        super(null, num);
    }

    /**
     * Marks the task in the arraylist.
     *
     * @param tasks the task to be marked in the arraylist.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.mark(num);
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
