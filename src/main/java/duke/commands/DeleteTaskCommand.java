package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteTaskCommand extends Command {
    private int indexToDelete;

    public DeleteTaskCommand(int index) {
        this.indexToDelete = index;
    }

    /**
     * Executes and deletes a task from the given TaskManager.
     * Returns true when the task is deleted, false otherwise.
     *
     * @param storage To Storage to save after the command executes.
     * @param ui The Ui to display the output to.
     * @param taskManager The TaskManager that contains the Task object.
     * @return true if command executed successfully, false otherwise.
     */
    public String execute(Storage storage, Ui ui, TaskManager taskManager) {

        if (taskManager.size() == 0) {
            return ui.showDeleteEmptyList();
        }

        if (this.indexToDelete < 0 || this.indexToDelete >= taskManager.size()) {
            return ui.showDeleteOutOfBounds(taskManager.size());
        }

        Task toDelete = taskManager.getTask(this.indexToDelete);
        boolean isSuccess = taskManager.deleteTask(this.indexToDelete);
        if (isSuccess) {
            save(storage, ui, taskManager);
            return ui.showDeletedTask(toDelete, taskManager.size());
        }
        return ui.showDeleteFailed();
    }
}
