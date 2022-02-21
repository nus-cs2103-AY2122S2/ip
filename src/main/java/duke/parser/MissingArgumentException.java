package duke.parser;

import duke.DukeException;

/**
 * Thrown when the user inputs a command with a missing piece of information.
 */
public class MissingArgumentException extends DukeException {
    public MissingArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
