package duke.command;

import duke.functionality.TaskList;

/**
 * Represents the exit command. A <code>ExitCommand</code> object corresponds to exiting the Duke program.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand class.
     */
    public ExitCommand() {
        super(null, null, null);
    }

    /**
     * Returns nothing & does not execute anything.
     * @param tasks an object of TaskList, used to access public methods in TaskList class.
     */
    @Override
    public void execute(TaskList tasks) { }

    /**
     * Returns true as the Command is an ExitCommand.
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
