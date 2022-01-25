package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import java.io.IOException;
import duke.DukeException;

public class InvalidCommand extends Command {
    @Override
    public void execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException {
        System.out.println("Please enter a valid command word (eg. list, mark, todo)!");
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
