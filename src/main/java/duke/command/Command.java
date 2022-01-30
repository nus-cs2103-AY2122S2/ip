package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;

/**
 * Represents a command. Inherited by specific command classes.
 *
 * @author Peter
 */
public abstract class Command {
    /**
     * Performs a unique execution for a specific command.
     *
     * @param taskList List of tasks that is to be operated on.
     * @param ui       UI responsible for displaying response message.
     * @param storage  Storage responsible for reading and writing to local file.
     * @return boolean value that determines process termination.
     * @throws DukeException If operation is unsuccessful.
     */
    public abstract boolean execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
