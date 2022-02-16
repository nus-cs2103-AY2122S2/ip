package bro.commands;

import bro.Storage;
import bro.TaskManager;
import bro.Ui;
import bro.exceptions.BroException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkDoneCommand extends Command {
    private int indexToMark;

    public MarkDoneCommand(int index) {
        this.indexToMark = index;
    }

    /**
     * Executes and marks the Task as not done. If an invalid number is provided,
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
                this.response = ui.showMarkEmptyList();
                return false;
            }

            if (this.indexToMark < 0 || indexToMark >= taskManager.size()) {
                this.response = ui.showMarkOutOfBounds();
                return false;
            }
            boolean isSuccess = taskManager.markTaskDone(indexToMark);
            if (!isSuccess) {
                this.response = ui.showMarkNotNeeded(taskManager.getTask(indexToMark));
                return false;
            }

            save(storage, ui, taskManager);
            this.response = ui.showMarked(taskManager.getTask(indexToMark));
            return true;
        } catch (NumberFormatException e) {
            this.response = ui.showInvalidIntegerError();
            return false;
        }
    }
}
