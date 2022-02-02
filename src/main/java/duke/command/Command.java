package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a command of the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public abstract class Command {

    /**
     * Execute the command stored in this instance.
     *
     * @param taskList The list of task in the Duke application.
     * @param storage  Storage of task in local persistent disk.
     * @throws  DukeException Custom error message by the Duke application.'
     * @exception IOException
     * @see IOException
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * This method is used to check if this command closes the Duke Application.
     *
     * @return This returns if this command closes the Duke application.
     */
    public boolean isExit() {
        return true;
    }
}
