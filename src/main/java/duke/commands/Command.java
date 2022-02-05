package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidOperationException;

/**
 * Skeletal implementation of a Command Object.
 */
public abstract class Command {

    /**
     * Checks and executes specific command.
     *
     * @return a String containing the relevant Task Object
     * @throws InvalidOperationException if operation is invalid
     */
    public abstract String execute() throws InvalidOperationException, DukeException;

}
