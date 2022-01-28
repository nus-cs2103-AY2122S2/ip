package duke.parser;

import duke.common.DukeException;

/**
 * Exception to handle cases where the command entered
 * is unknown/invalid.
 */
public class UnkownCommandException extends DukeException {

    /**
     * Constructor to create a UnknownCommandException.
     */
    public UnkownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
