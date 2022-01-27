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
        super(null, num,null);
    }

    /**
     * Marks the task in the arraylist.
     *
     * @param tasks the task to be marked in the arraylist.
     */
    @Override
    public void execute(TaskList tasks) {
        try {
            tasks.mark(num);
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
