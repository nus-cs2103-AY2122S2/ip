package bro.commands;

import bro.Storage;
import bro.TaskManager;
import bro.Ui;
import bro.exceptions.BroException;

/**
 * Represents a general command entered by the user.
 *
 * @see AddCommand
 * @see DeleteTaskCommand
 * @see ExitCommand
 * @see ListCommand
 * @see SortByDateCommand
 * @see SortByNameCommand
 */
public abstract class Command {

    protected String response = "";

    public Command() { }

    /**
     * Saves the given Tasks in the given TaskManager to the storage.
     * Shows a success message to the Ui when saved successfully,
     * or an error message otherwise.
     *
     * @param storage The storage to save the TaskManager to.
     * @param ui The Ui to display after saving success or failure.
     * @param taskManager The TaskManager to save.
     */
    protected void save(Storage storage, Ui ui, TaskManager taskManager) {
        try {
            storage.saveTaskManager(taskManager);
            ui.showSavingComplete();
        } catch (BroException e) {
            ui.showSavingFailed();
        }
    }

    /**
     * Executes the given command type.
     *
     * @param storage The storage to save the TaskManager to if required.
     * @param ui The Ui to display the output of the command to.
     * @param taskManager The TaskManager containing the tasks.
     * @return true if the command executes successfully, false otherwise.
     * @throws BroException If there is an error encountered during execution.
     */
    public abstract boolean execute(Storage storage, Ui ui, TaskManager taskManager);

    public String getResponse() {
        return this.response;
    }

    public boolean isExit() {
        return false;
    }
}
