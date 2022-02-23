package sparrow.logic.commands;

import sparrow.commons.exceptions.SparrowException;
import sparrow.model.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;


/**
 * Represents the command to exit the program.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Executes the command.
     *
     * @param storage Storage of Duke.
     * @param tasks   Task list of Duke.
     * @param ui      User interface of Duke.
     * @throws SparrowException if there is a problem updating the storage or user interface.
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) throws SparrowException {
        return ui.exit(storage, tasks);
    }

    /**
     * Indicates whether the program should stop after this command.
     *
     * @return Boolean indicating whether the program should stop after this command.
     */
    public boolean isExit() {
        return true;
    }
}
