package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates command to delete a task from task list
 */
public class DeleteCommand extends Command {
    /**
     * The index of task to be deleted
     */
    private final int task;

    /**
     * Instantiates a new Delete command.
     *
     * @param task index of task to be deleted
     */
    public DeleteCommand(int task) {
        super();
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        try {
            Task t = tasks.remove(this.task);
            ui.showMessage("Noted. I've removed the task:");
            ui.showMessage(t.toString());
            storage.updateStorage(tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Could not delete task as it was not found.");
        }
    }
}
