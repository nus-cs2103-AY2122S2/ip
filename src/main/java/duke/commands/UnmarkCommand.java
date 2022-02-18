package duke.commands;

import duke.data.TasksEditor;
import duke.data.task.Task;
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
    public String execute(TasksEditor tasksEditor, Ui ui, Storage storage) {
        Task t = tasksEditor.unmark(index);
        return ui.unmark(t);
    }
}