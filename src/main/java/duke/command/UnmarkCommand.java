package duke.command;

import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructs an {@code UnmarkCommand} object.
     * @param index the index of the task to unmark
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks the task at the specified index in the list of tasks.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index <= tasks.size()) {
            tasks.set(index - 1, tasks.get(index - 1).unmark());
            ui.showMessage("OK, I've marked this task as not done yet:\n"
                    + tasks.get(index - 1));
        } else {
            ui.showMessage("Index is invalid");
        }
    }

}
