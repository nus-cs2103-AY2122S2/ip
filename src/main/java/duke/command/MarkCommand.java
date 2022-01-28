package duke.command;

import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructs a {@code MarkCommand} object.
     * @param index the index of the task to mark as done
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index in the list of tasks as done.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index <= tasks.size()) {
            tasks.set(index - 1, tasks.get(index - 1).mark());
            ui.showMessage("Nice! I've marked this task as done:\n"
                    + tasks.get(index - 1));
        } else {
            ui.showMessage("Index is invalid");
        }
    }

}
