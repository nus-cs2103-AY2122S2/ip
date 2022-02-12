package duke.command;

import duke.DukeException;
import duke.task.TaskList;

/**
 * An abstract class for Commands
 */
public abstract class Command {

    /**
     * Constructs a {@code Command} object.
     */
    public Command() {}

    /**
     * Executes the command.
     * @param tasks current list of tasks
     */
    public abstract String execute(TaskList tasks) throws DukeException;

    /**
     * Indicates whether the program should be exited.
     * @return true if the program should be exited
     */
    public abstract boolean isExit();

}
