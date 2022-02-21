package seedu.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.exception.DukeException;

/**
 * Command class with run method to be implemented.
 */
public abstract class Command {
    /**
     * Executes the command.
     */
    public abstract String run(TaskList tasksList, Storage storage) throws DukeException;
}
