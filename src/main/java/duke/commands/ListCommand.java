package duke.commands;

import duke.data.TasksEditor;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD;

    @Override
    public String execute(TasksEditor tasksEditor, Ui ui, Storage storage) {
        return ui.list(tasksEditor);
    }
}
