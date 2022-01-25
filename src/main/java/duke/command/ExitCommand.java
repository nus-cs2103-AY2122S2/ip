package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.DukeException;

public class ExitCommand extends Command {

    @Override
    public void execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException {
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
