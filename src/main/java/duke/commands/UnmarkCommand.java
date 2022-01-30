package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Sets a task as not done yet.
 */
public class UnmarkCommand extends Command {
    protected int index;
    private static final String MESSAGE = "Nice! I've marked this task as not done yet:";

    /**
     * Constructs an unmark command.
     *
     * @param index The index of task in the list.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkTask(index);
        storage.saveTaskList(tasks);
        ui.showMessage(MESSAGE + "\n  " + tasks.getTaskString(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
