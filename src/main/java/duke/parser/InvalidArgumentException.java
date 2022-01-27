package duke.parser;

import duke.common.DukeException;

/**
 * Exception to handle cases where invalid arguments are passed
 * along with a command.
 */
public class InvalidArgumentException extends DukeException {

    /**
     * Constructor to create a InvalidArgumentException.
     *
     * @param errorMessage message describing the error.
     */
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
