package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        ui.showExit(storage, tasks);
    }

    public boolean isExit() {
        return true;
    }
}
