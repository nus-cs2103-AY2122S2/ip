package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class UnknownCommand extends Command {
    private static final String MESSAGE = "I'm sorry, but I don't know what that means :-(";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(MESSAGE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
