package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the command and returns the result.
     *
     * @param tasks the TaskList containing the current tasks
     * @param ui the Ui of the chatbot
     * @param storage the storage of the chatbot
     * @return the result of execution
     * @throws DukeException if there were any errors during execution
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise
     */
    public abstract boolean isExit();
}
