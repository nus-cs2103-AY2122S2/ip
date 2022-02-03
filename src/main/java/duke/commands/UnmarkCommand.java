package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.data.task.Task;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the mark of the task identified by the index number.\n\t\t"
            + "Parameters: INDEX\n\t\t"
            + "Example: " + COMMAND_WORD + " 1";

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.unmark(index);
        ui.unmark(t);
    }
}