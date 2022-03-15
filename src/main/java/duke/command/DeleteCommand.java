package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command that deletes a task from the user's task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private int index;

    /**
     * Creates a new DeleteCommand.
     *
     * @param index index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes a task from the user's task list, updates the task list on user's storage and notifies the
     * user upon successful completion.
     *
     * @param tasks List of user's tasks.
     * @param storage Handles storage of tasks in the user's memory.
     *
     * @throws DukeException On invalid task index.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            Task task = tasks.deleteTask(index);
            storage.save(tasks);
            return Ui.showDeleteTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task " + (index + 1) + " does not exist bro.");
        }
    }
}
