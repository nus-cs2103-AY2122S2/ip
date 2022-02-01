package duke.commands;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Requires individual command that extends this class to implement said methods.
 */
public abstract class Command {
    /**
     * Executes the individual command.
     * @param taskList taskList holds the list of tasks and its Create, Retrieve, Update, Delete functions.
     * @param storage storage handles storage reads and writes.
     * @return Output message for GUI.
     * @throws DukeException Throw when an error occurs during command execution.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DukeException;

    /**
     * Notifies main class when user wants to exit the program.
     * @return Returns false always until exit command is run.
     */
    public boolean isExit() {
        return false;
    }
}
