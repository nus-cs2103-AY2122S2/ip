package duke.parser;

import duke.DukeException;

/**
 * Thrown when the user input is not a valid command.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
