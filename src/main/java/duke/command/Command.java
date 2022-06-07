package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a general command that is the superclass of all classes representing a specific command.
 */
public abstract class Command {
    /**
     * Carries out the actions correspond to this command.
     *
     * @param ui user interface of the application.
     * @param taskList task list of the application.
     * @param storage disk storage of the application.
     * @throws DukeException when an exception is thrown in the process of executing this command.
     */
    public abstract void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException;

    /**
     * Checks if this command is a goodbye command.
     *
     * @return false.
     */
    public boolean isBye() {
        return false;
    }
}
