package duke.commands;

import duke.exceptions.DukeException;
import duke.Storage;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a general command entered by the user.
 *
 * @see AddCommand
 * @see DeleteTaskCommand
 * @see ExitCommand
 * @see ListCommand
 * @see MarkCommand
 * @see SortByDateCommand
 * @see SortByNameCommand
 */
public abstract class Command {
    Storage storage = new Storage();
    Ui ui = new Ui();
    TaskManager taskManager = new TaskManager();
    String userInput = "";

    public Command(String userInput) {
        this.userInput = userInput;
    }
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
        } catch (DukeException e) {
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
     * @throws DukeException If there is an error encountered during execution.
     */
    public abstract String execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
