package bro.commands;

import bro.Storage;
import bro.TaskManager;
import bro.Ui;
import bro.tasks.Task;

/**
 * Represents a command to update the name of the task.
 */
public class UpdateNameCommand extends UpdateCommand {
    private int indexToUpdate;
    private String newName;

    /**
     * Constructor for Update Command with LocalDateTime object.
     *
     * @param index The index of the task to update (0 Indexed).
     * @param newName The string that represents the new name of the task.
     */
    public UpdateNameCommand(int index, String newName) {
        this.indexToUpdate = index;
        this.newName = newName.strip();
    }

    /**
     * Executes and updates the name of the task.
     *
     * @param storage The storage to save the TaskManager to if required.
     * @param ui The Ui to display the output of the command to.
     * @param taskManager The TaskManager containing the tasks.
     * @return True if the command executed successfully, false otherwise.
     */
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) {

        assert newName != null : "New name is null in UpdateNameCommand!";

        if (newName.strip().equals("")) {
            this.response = ui.showNoName();
            return false;
        }

        if (taskManager.size() == 0) {
            this.response = ui.showMarkEmptyList();
            return false;
        }

        if (indexToUpdate < 0 || indexToUpdate >= taskManager.size()) {
            this.response = ui.showUpdateOutOfBounds();
            return false;
        }

        Task toUpdate = taskManager.getTask(indexToUpdate);
        boolean isSuccess = toUpdate.updateName(newName);

        if (!isSuccess) {
            this.response = ui.showSameNameError();
            return false;
        }

        save(storage, ui, taskManager);
        this.response = ui.showUpdateSuccess(toUpdate);
        return true;
    }
}
