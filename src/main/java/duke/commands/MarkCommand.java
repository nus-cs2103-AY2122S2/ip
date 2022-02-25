package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates command to mark task as completed
 */
public class MarkCommand extends Command{
    /**
     * The index of task to be marked as completed.
     */
    private final int task;

    /**
     * Instantiates a new Mark command.
     *
     * @param task the index of the task
     */
    public MarkCommand(int task) {
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
            tasks.markDone(task, true);
            ui.showMessage("OK. I've marked this task as done:");
            ui.showMessage(tasks.get(task).toString());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Please enter a valid task. Task " + this.task + " does not exist.");
        }
    }
}
