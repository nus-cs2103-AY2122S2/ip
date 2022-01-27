package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Represents a Command which, when executed, deletes a Task from the given TaskList instance.
 */
public class DeleteCommand extends Command {
    private static final boolean IS_EXIT = false;
    private int taskIndex;

    /**
     * Creates a new DeleteCommand instance with the initialized index.
     *
     * @param taskIndex Zero based index of the Task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        super(IS_EXIT);
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the Task object to the TaskList and displays the deleted Task on Ui.
     * Displays the error message on Ui if the given Task cannot be deleted successfully.
     *
     * @param tasks The TaskList instance in which the Task object is deleted from.
     * @param ui The Ui object used for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(taskIndex);
            tasks.delete(taskIndex);
            String message = String.format("%s\n  %s\nThere are %d tasks in the burning list.",
                    Ui.REMOVE_MESSAGE, task.toString(), tasks.getSize());
            ui.appendMessage(message);
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}
