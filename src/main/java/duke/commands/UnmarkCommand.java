package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates command to mark a task as incomplete.
 */
public class UnmarkCommand extends Command{
    /**
     * The index of the task to be marked as incompleted.
     */
    private final int task;

    /**
     * Instantiates a new Unmark command.
     *
     * @param task the index of the task
     */
    public UnmarkCommand(int task) {
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
            tasks.markDone(task, false);
            ui.showMessage("OK. I've marked this task as not-yet-done:");
            ui.showMessage(tasks.get(task).toString());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Please enter a valid task. Task " + this.task + " does not exist.");
        }
    }
}
