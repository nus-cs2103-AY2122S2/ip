package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.DukeException;

import java.io.IOException;

public class ListCommand extends Command {
    @Override
    public void execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException {
        System.out.println("Here are the tasks in your list:");
        ui.displayList(tasks.getCount(), tasks);
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
