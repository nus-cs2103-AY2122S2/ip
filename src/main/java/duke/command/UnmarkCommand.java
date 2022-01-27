package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that marks the given task as incomplete.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs a UnmarkCommand object that marks the given task as complete upon execution.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the given task as incomplete, displays the result to the user, and saves the change to the list.
     *
     * @param taskList The list of tasks.
     * @param ui The UI object responsible for user interaction.
     * @param storage The Storage object responsible for saving the change.
     * @throws DukeException If the indexed task does not exist or the change cannot be saved.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task selectedTask = taskList.getTask(index);
        selectedTask.markAsIncomplete();
        ui.displayUnmarkedTask(selectedTask);
        storage.write(taskList.getTasks());
    }
}
