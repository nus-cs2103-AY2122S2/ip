package duke.storage;

import duke.common.DukeException;

/**
 *  Exception thrown when file follows and incorrect format
 *  or syntax.
 */
public class InvalidFileSyntaxException extends DukeException {

    /**
     * Constructs a InvalidFileSyntaxException.
     *
     * @param errorMessage message describing the error.
     */
    public InvalidFileSyntaxException(String errorMessage) {
        super(errorMessage);
    }
}
