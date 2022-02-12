package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.List;

/**
 * Represents a Command object that will clear all tasks in data and
 * the tasklist.
 */
public class ClearCommand extends Command {

    /**
     * Clears all tasks in the tasklist.
     *
     * @param stg   The storage object to use file writing methods.
     * @param ui    The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @return Successful clearing of data message.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public String execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException {
        stg.clearData();
        stg.clearStack();
        tasks.clearAllTask();
        return "All data cleared!";
    }

    /**
     * Returns boolean value to state if the app should exit its run.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
