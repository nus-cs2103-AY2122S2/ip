package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.DukeException;

/**
 * Represents a Command object that will inform users
 * of an invalid input.
 */

import java.io.IOException;

public class InvalidCommand extends Command {

    /**
     * Returns an invalid command message.
     *
     * @param stg   The storage object to use file writing methods.
     * @param ui    The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @return Invalid command message.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public String execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException {
        System.out.println("Please enter a valid command word (eg. list, mark, todo)!");
        ui.showLine();
        return "Please enter a valid command word!";
    }

    /**
     * Returns boolean value to state if the app should exit its run.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
