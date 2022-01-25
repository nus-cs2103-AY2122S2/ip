package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.DukeException;
import ui.Ui;

public class NotKnownCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showError("OOPS!!! I'm sorry, but I don't know what that means :<");

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
