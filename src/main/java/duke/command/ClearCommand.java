package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a Command object that will clear all tasks in data and
 * the tasklist.
 */
public class ClearCommand extends Command {

    /**
     * Will clear all tasks in data and tasklist.
     *
     * @param stg   The storage object to use file writing methods.
     * @param ui    The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException {
        System.out.println("About to clear all data. Enter yes to confirm");
        String input = ui.readFullLine();
        if (input.equals("yes")) {
            stg.clearData();
            tasks.clearAllTask();
            System.out.println("All data cleared!");
        }
        ui.showLine();
        return;
    }

    /**
     * Signifies to the app to not terminate its current run.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
