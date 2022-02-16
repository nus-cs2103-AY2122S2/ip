package bro.commands;

import bro.Storage;
import bro.TaskManager;
import bro.Ui;
import bro.exceptions.BroException;

/**
 * Represents a command to mark a task as not done.
 */
public class MarkUndoneCommand extends Command {
    private int indexToUnmark;

    public MarkUndoneCommand(int index) {
        this.indexToUnmark = index;
    }

    /**
     * Executes and marks the Task as done. If an invalid number is provided,
     * throws a Duke Exception containing the error message. Returns true
     * if executed successfully, false otherwise.
     *
     * @param storage To Storage to save after the command executes.
     * @param ui The Ui to display the output to.
     * @param taskManager The TaskManager that contains the Task object.
     * @return true if command executed successfully, false otherwise.
     * @throws BroException If index entered is not a number.
     */
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) {
        try {
            if (taskManager.size() == 0) {
                this.response = ui.showUnmarkEmptyList();
                return false;
            }

            if (indexToUnmark < 0 || indexToUnmark >= taskManager.size()) {
                this.response = ui.showUnmarkOutOfBounds();
                return false;
            }
            boolean isSuccess = taskManager.markTaskUndone(indexToUnmark);
            if (!isSuccess) {
                this.response = ui.showUnmarkNotNeeded(taskManager.getTask(indexToUnmark));
                return false;
            }

            save(storage, ui, taskManager);
            this.response = ui.showUnmarked(taskManager.getTask(indexToUnmark));
            return true;
        } catch (NumberFormatException e) {
            this.response = ui.showInvalidIntegerError();
            return false;
        }
    }
}
