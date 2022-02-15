package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.TaskStack;
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
     * @param ui       UI responsible for displaying response from Duke.
     * @param storage  Storage responsible for reading and writing to local file.
     * @return String response from Duke upon successful execution.
     * @throws DukeException If operation is unsuccessful.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage,
                                   TaskStack taskStack) throws DukeException;

    public abstract boolean isExitCommand();
}
