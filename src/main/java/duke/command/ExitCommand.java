package duke.command;

import duke.operations.TaskList;

/**
 * Represents a subclass of Command.
 */
public class ExitCommand extends Command {
    /**
     * A constructor for <code>ExitCommand</code>.
     */
    public ExitCommand() {
        super(null, null, null);
    }

    /**
     * Exits Duke.
     *
     * @param tasks none.
     */
    @Override
    public void execute(TaskList tasks) {

    }

    /**
     * Checks whether it is the exit command.
     *
     * @return if false, continue receiving input, else terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
