package duke.commands;

import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.task.Task;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number.\n\t\t"
            + "Parameters: INDEX\n\t\t"
            + "Example: " + COMMAND_WORD + " 1";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.delete(index);
        ui.delete(t, tasks.getSize());
    }
}
