package duke.command;

import duke.exception.InvalidArgumentException;
import duke.task.TaskList;

/**
 * Represents a command that the user can interact with the task bot. A
 * <code>Command</code> object cannot be created as it is an abstract base class
 * for all commands.
 */
public abstract class Command {

    /**
     * Returns a command feedback after the execution of the Command.
     * @param taskList a list of task.
     * @return a command feedback of CommandType.
     * @throws InvalidArgumentException if index of task is out of bounds.
     */
    public abstract CommandFeedback execute(TaskList taskList) throws InvalidArgumentException;

    /**
     * Returns the command word of the Command.
     *
     * @return command word.
     */
    public abstract String getCommandWord();
}
