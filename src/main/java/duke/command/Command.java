package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     *
     * @param tasks TaskList of the current running application.
     * @param ui Ui of the current running application.
     * @param storage Storage of the current running application.
     * @throws DukeException if any error arises when executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}