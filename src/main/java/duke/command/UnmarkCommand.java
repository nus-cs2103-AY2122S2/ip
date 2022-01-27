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
        super(null, num,null);
    }

    /**
     * Unmarks the task in the arraylist.
     *
     * @param tasks the task to be marked in the arraylist.
     */
    @Override
    public void execute(TaskList tasks) {
        try {
            tasks.unmark(num);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task number is kinda sus... it's outta bounds!");
        }
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
