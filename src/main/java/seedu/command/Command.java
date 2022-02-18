package seedu.command;

import seedu.duke.Storage;
import seedu.exception.DukeException;
import seedu.duke.TaskList;

/**
 * Skeleton of Command class with run method to be implemented.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @return Output message for GUI.
     */
    public abstract String run(TaskList tasksList, Storage storage) throws DukeException;
}
