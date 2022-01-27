package duke.command;

import java.util.List;
import duke.exception.DukeException;
import duke.task.Task;
import duke.Ui;

/**
 * Abstract class to define Command superclass methods.
 */
public abstract class Command {

    /**
     * Abstract method for execution of every command.
     *
     * @param tasks Task list
     * @param ui UI object
     * @throws DukeException Depending on implementation of the different tasks, throw for errors
     */
    public abstract void execute(List<Task> tasks, Ui ui) throws DukeException;

    /**
     * Default method to check if command should exit.
     * Should always return false for most commands except the exit command.
     *
     * @return Always false unless overridden by specific command implementation.
     */
    public boolean isExit() {
        return false;
    }
}
