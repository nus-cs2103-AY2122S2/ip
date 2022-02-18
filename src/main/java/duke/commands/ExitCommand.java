package duke.commands;

import java.io.IOException;

import duke.data.TasksEditor;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command{
    
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n\t"
            + "Example: " + COMMAND_WORD;

    @Override
    public String execute(TasksEditor tasksEditor, Ui ui, Storage storage) throws IOException {
        storage.store(tasksEditor.toList());
        return ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
