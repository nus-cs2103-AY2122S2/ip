package duke.commands;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Requires individual command that extends this class to implement said methods.
 */
public abstract class Command {
    /**
     * Executes the individual command.
     * @param taskList taskList holds the list of tasks and its Create, Retrieve, Update, Delete functions.
     * @param ui ui displays feedback to the user.
     * @param storage storage handles storage reads and writes.
     * @throws DukeException Throw when an error occurs during command execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Notifies main class when user wants to exit the program.
     * @return Returns false always until exit command is run.
     */
    public boolean isExit() {
        return false;
    }
}
