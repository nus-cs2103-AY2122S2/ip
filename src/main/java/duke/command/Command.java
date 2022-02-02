package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.MessageUi;

/**
 * Represents an abstract class for the various commands that Ekud supports.
 */

public interface Command {

    /**
     * Execute the function of the command.
     *
     * @param tasks   Task object.
     * @param storage Storage object.
     * @param ui      Ui object.
     * @throws Exception If directory or file cannot be found.
     */
    public abstract String execute(TaskList tasks, Storage storage, MessageUi ui) throws DukeException;
}
