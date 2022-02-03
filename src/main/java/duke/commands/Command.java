package duke.commands;

import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.DukeException;

import java.io.IOException;

/**
 * Represents an executable command.
 */
public class Command {
    protected Command() {
    }

    /**
     * Executes the command and returns the result.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        throw new DukeException("This method is to be implemented by child classes");
    };

    public boolean isExit() {
        return false;
    }
}
