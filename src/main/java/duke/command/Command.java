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
     * Returns false if the command is not an ExitCommand.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command based on the subclass.
     *
     * @param tasks TaskList of the current running application.
     * @param ui Ui of the current running application.
     * @param storage Storage of the current running application.
     * @throws DukeException if any error arises when executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}