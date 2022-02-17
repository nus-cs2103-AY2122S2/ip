package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.DukeException;
import duke.ui.Ui;

/**
 * Represents abstract command class that perform different type of commands.
 */
public abstract class Command {

    /**
     * Executes the respective commands.
     *
     * @param tasks List of the tasks.
     * @param ui UI that deals with interactions with the user.
     * @param storage storage handles the saving and writing to file.
     * @throws DukeException exception thrown when there is error occurs.
     * @throws IOException exception thrown when there is error occurs.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * returns true if is ExitCommand. Else false.
     *
     * @return true if is ExitCommand. Else false.
     */
    public abstract boolean isExit();

}
