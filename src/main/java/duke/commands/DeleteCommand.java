package duke.commands;

import duke.data.task.Task;
import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Add a todo to the tasklist.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number.\n"
            + "Parameters: INDEX\n"
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
