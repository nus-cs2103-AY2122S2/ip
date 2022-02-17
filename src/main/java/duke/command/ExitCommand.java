package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command to exit Duke.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for the exit command.
     */
    public ExitCommand() {}

    /** {@inheritDoc} */
    @Override
    public String exec(TaskList taskList, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Checks if Duke should exit.
     *
     * @return true since this is the Exit Command.
     */
    @Override
    public boolean shouldAbort() {
        return true;
    }
}
