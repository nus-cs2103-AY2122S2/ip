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
     * Returns a string which contains the message to stop the execution of Duke.
     * @param tasks an object of TaskList, used to access public methods in TaskList class.
     * @return crafted message to signify the end of execution.
     */
    @Override
    public String execute(TaskList tasks) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns true as the Command is an ExitCommand.
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
