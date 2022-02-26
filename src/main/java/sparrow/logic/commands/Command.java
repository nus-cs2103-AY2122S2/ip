package sparrow.logic.commands;

import sparrow.commons.exceptions.SparrowException;
import sparrow.model.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

/**
 * Represents a command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param storage the storage.
     * @param tasks   the task list.
     * @param ui      the user interface.
     * @throws SparrowException if there is a problem updating the storage or user interface.
     */
    public abstract String execute(Storage storage, TaskList tasks, Ui ui) throws SparrowException;

    /**
     * Indicates whether the program should stop after this command.
     *
     * @return Boolean indicating whether the program should stop after this command.
     */
    public abstract boolean isExit();
}
