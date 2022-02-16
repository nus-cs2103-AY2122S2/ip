package bro.commands;

import java.time.LocalDateTime;

import bro.Storage;
import bro.TaskManager;
import bro.Ui;
import bro.tasks.Task;

/**
 * Represents a Command to update the date of the task.
 */
public class UpdateDateCommand extends UpdateCommand {
    private int indexToUpdate;
    private LocalDateTime newDate;
    private String newDateString;

    /**
     * Constructor for Update Command with LocalDateTime object.
     * @param index The index of the task to update (0 Indexed).
     * @param newDate The LocalDateTime object that represents the new date of the task.
     */
    public UpdateDateCommand(int index, LocalDateTime newDate) {
        this.indexToUpdate = index;
        this.newDate = newDate;
    }

    /**
     * Constructor for Update Command with LocalDateTime object.
     *
     * @param index The index of the task to update (0 Indexed).
     * @param newDateString The string that represents the new date of the task.
     */
    public UpdateDateCommand(int index, String newDateString) {
        this.indexToUpdate = index;
        this.newDateString = newDateString.strip();
    }

    /**
     * Executes and updates the task indicated with the new date.
     *
     * @param storage The storage to save the TaskManager to if required.
     * @param ui The Ui to display the output of the command to.
     * @param taskManager The TaskManager containing the tasks.
     * @return True if the command executed successfully, false otherwise.
     */
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) {

        if (taskManager.size() == 0) {
            this.response = ui.showMarkEmptyList();
            return false;
        }

        if (indexToUpdate < 0 || indexToUpdate >= taskManager.size()) {
            this.response = ui.showUpdateOutOfBounds();
            return false;
        }

        Task toUpdate = taskManager.getTask(indexToUpdate);

        if (toUpdate.getType() == 'T') {
            this.response = ui.showIncompatibleType();
            return false;
        }

        boolean isSuccess;

        if (newDate != null) {
            /* Recognizable date format entered */
            isSuccess = toUpdate.updateDate(newDate);
        } else {
            isSuccess = toUpdate.updateDate(newDateString);
        }

        if (!isSuccess) {
            this.response = ui.showSameDateError();
            return false;
        }

        save(storage, ui, taskManager);
        this.response = ui.showUpdateSuccess(toUpdate);
        return true;
    }
}
