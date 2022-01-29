package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("Invalid command.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
