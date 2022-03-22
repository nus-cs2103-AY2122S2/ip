package duke.commands;

import duke.data.TasksEditor;
import duke.data.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TasksEditor tasksEditor, Ui ui, Storage storage) {
        Task t = tasksEditor.delete(index);
        return ui.delete(t, tasksEditor.getSize());
    }
}
