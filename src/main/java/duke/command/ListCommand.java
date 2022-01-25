package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.DukeException;
import java.io.IOException;

/**
 * Represents a Command object that will list out all tasks
 * in the tasklist.
 */
public class ListCommand extends Command {

    /**
     * Will list out all tasks items in the tasklist.
     *
     * @param stg The storage object to use file writing methods.
     * @param ui The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException {
        ui.displayList(tasks.getCount(), tasks);
        ui.showLine();
    }

    /**
     * Signifies to the app to not terminate its current run.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
