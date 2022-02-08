package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Provides feedback if the input command is not recognised.
 */
public class UnknownCommand extends Command {
    private static final String MESSAGE = "I'm sorry, but I don't know what that means :-(";

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showMessage(MESSAGE);
    }
}
