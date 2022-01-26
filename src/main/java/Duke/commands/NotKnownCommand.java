package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.DukeException;
import duke.ui.Ui;

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
