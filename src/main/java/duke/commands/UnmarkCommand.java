package duke.commands;

import duke.data.task.Task;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the mark of the task identified by the index number.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1";

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.unmark(index);
        return ui.unmark(t);
    }
}