package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that deletes a task from the list of tasks.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand object that deletes a task from the task list upon execution.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the given task from the list, displays the result to the user, and saves the change to the list.
     *
     * @param taskList The list of tasks.
     * @param ui The UI object responsible for user interaction.
     * @param storage The Storage object responsible for saving the change.
     * @throws DukeException If the indexed task does not exist or the change cannot be saved.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = taskList.deleteTask(index);
        ui.displayDeletedTask(deletedTask);
        ui.displayNumberOfTasks(taskList.getTasks());
        storage.write(taskList.getTasks());
    }
}
