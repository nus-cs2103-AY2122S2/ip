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
     * Deletes the given task to the list, saves the change, and returns a message highlighting the change.
     *
     * @param taskList The list of tasks.
     * @param ui The UI object responsible for user interaction.
     * @param storage The Storage object responsible for saving the change.
     * @return The message informing the user of the deleted task.
     * @throws DukeException If the indexed task does not exist or the change cannot be saved.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = taskList.deleteTask(index);
        storage.write(taskList.getTasks());

        return ui.displayDeletedTask(deletedTask) + "\n" + ui.displayNumberOfTasks(taskList.getTasks());
    }
}
