package duke.commands;

import duke.data.TasksEditor;
import duke.storage.Storage;
import duke.ui.Ui;

public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo the last operation on the task list.\n\t"
            + "Example: " + COMMAND_WORD;

    @Override
    public String execute(TasksEditor tasksEditor, Ui ui, Storage storage) {
        boolean isSuccessful = tasksEditor.undo();
        return ui.showUndo(tasksEditor, isSuccessful);
    }
}
