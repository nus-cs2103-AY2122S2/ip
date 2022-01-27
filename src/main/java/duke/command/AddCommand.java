package duke.command;

import duke.storage.FileSaveException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that adds a task to the list of tasks.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand object that adds a task to the task list upon execution.
     * @param task The task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the given task to the list, displays the result to the user, and saves the change to the list.
     * @param taskList The list of tasks
     * @param ui The UI object responsible for user interaction
     * @param storage The Storage object responsible for saving the change
     * @throws FileSaveException If the change cannot be saved
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FileSaveException {
        taskList.addTask(task);
        ui.displayAddedTask(task);
        ui.displayNumberOfTasks(taskList.getTasks());
        storage.write(taskList.getTasks());
    }
}
