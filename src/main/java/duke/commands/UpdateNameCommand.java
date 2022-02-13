package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.tasks.Task;

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
     * @return The output of the command.
     */
    public String execute(Storage storage, Ui ui, TaskManager taskManager) {

        if (newName.strip().equals("")) {
            return ui.showNoName();
        }

        if (taskManager.size() == 0) {
            return ui.showMarkEmptyList();
        }

        if (indexToUpdate < 0 || indexToUpdate >= taskManager.size()) {
            return ui.showUpdateOutOfBounds();
        }

        Task toUpdate = taskManager.getTask(indexToUpdate);
        boolean isSuccess = toUpdate.updateName(newName);

        if (!isSuccess) {
            return ui.showSameNameError();
        }

        save(storage, ui, taskManager);
        return ui.showUpdateSuccess(toUpdate);
    }
}
