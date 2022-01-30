package duke.command;

import duke.exception.DukeException;
import duke.function.Storage;
import duke.function.TaskList;
import duke.function.Ui;

/**
 * Represents commands that are formed from user input and can be executed.
 * Meant to be extended and implemented by subclasses.
 */
public abstract class Command {
    /**
     * The full command input by the user.
     */
    private String fullCommand;

    Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Returns whether the current command is an exit command to terminate the application.
     *
     * @return
     */
    public abstract boolean isExit();

    /**
     * Executes the current command with Duke's current state.
     *
     * @param tasks   The current tasks for the command to interact with.
     * @param ui      The ui for the command to print output.
     * @param storage The storage for the command to save and load tasks to an external file.
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
