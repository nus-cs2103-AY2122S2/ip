package duke.command;

import duke.DukeException;
import duke.task.TaskList;

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

    public abstract boolean isExit();

}
