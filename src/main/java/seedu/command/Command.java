package seedu.command;

import java.io.IOException;

import seedu.duke.Storage;
import seedu.exception.CommandException;
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
    public abstract String run(TaskList tasks, Storage storage) throws DukeException;
}
