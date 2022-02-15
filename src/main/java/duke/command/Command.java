package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {
    /**
     * Returns False if the user has not decided to exit the application.
     *
     * @return False if the command is not an ExitCommand, otherwise True.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command based on the subclass and returns the string result.
     *
     * @param tasks TaskList of the current running application.
     * @param ui Ui of the current running application.
     * @param storage Storage of the current running application.
     * @return The string result.
     * @throws DukeException If any error arises when executing the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
