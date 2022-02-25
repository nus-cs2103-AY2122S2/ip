package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates command to mark a task as incomplete.
 */
public class UnmarkCommand implements Command {
    /**
     * The index of the task to be marked as incompleted.
     */
    private final int index;

    /**
     * Instantiates a new Unmark command.
     *
     * @param index the index of the task
     */
    public UnmarkCommand(int index) {
        super();
        this.index = index;
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
            tasks.markDone(index, false);
            ui.showMessage("OK. I've marked this task as not-yet-done:");
            ui.showMessage(tasks.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMessage("Please enter a valid task. Task " + this.index + " does not exist.");
        }
    }
}
