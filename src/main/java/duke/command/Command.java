package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.DukeException;

import java.io.IOException;

/**
 * Represents a Command object that will act in a specified
 * way.
 */
public abstract class Command {

    /**
     * Executes an operation based on the command type.
     *
     * @param stg The storage object to use file writing methods.
     * @param ui The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @return Message on the outcome of the command.
     * @throws DukeException If any error related to the Duke app occurs.
     * @throws IOException If an I/O error occurs.
     */
    public abstract String execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException;

    /**
     * Returns boolean value to state if the app should exit its run.
     */
    public abstract boolean isExit();

}
