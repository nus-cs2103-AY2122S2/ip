package duke.command;

import duke.operations.TaskList;

/**
 * Represents a subclass of Command.
 */
public class PrintCommand extends Command {
    /**
     * A constructor for <code>PrintCommand</code>.
     */
    public PrintCommand() {
        super(null, null, null);
    }

    /**
     * Prints the tasks in the arraylist.
     *
     * @param tasks the tasks to be printed.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.printList();
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
