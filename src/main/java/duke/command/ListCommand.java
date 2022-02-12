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
     * Returns a list to show all tasks items in the tasklist.
     *
     * @param stg The storage object to use file writing methods.
     * @param ui The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @return The list of tasks currently in the list.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public String execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException {
        ui.displayList(tasks.getCount(), tasks);
        ui.showLine();
        return ui.showListedTasks(tasks.getCount(), tasks);
    }

    /**
     * Returns boolean value to state if the app should exit its run.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
