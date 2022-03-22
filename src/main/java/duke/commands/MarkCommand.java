package duke.commands;

import duke.data.TasksEditor;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number as done.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1";

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TasksEditor tasksEditor, Ui ui, Storage storage) {
        Task t = tasksEditor.mark(index);
        return ui.mark(t);
    }
}
