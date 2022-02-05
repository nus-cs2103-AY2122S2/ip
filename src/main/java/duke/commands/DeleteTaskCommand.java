package duke.commands;

import duke.Storage;
import duke.tasks.Task;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteTaskCommand extends Command {
    public DeleteTaskCommand(String userInput) {
        super(userInput);
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
    @Override
    public String execute(Storage storage, Ui ui, TaskManager taskManager) {
        if (taskManager.size() == 0) {
            return ui.showDeleteEmptyList();
        }

        int index = Integer.parseInt(userInput.replaceFirst("delete","").strip()) - 1;

        if (index < 0 || index >= taskManager.size()) {
            return ui.showDeleteOutOfBounds(taskManager.size());
        }

        Task t = taskManager.getTask(index);
        boolean success = taskManager.deleteTask(t);
        if (success) {
            save(storage, ui, taskManager);
            return ui.showDeletedTask(t, taskManager.size());
        }
        return ui.showDeleteFailed();
    }
}
