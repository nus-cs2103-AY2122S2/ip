package luca.parser.exceptions;

import luca.common.DukeException;

/**
 * Exception thrown when the user inputs the data and time invalid format.
 */
public class InvalidDateTimeFormatException extends DukeException {

    /**
     * Constructor to create InvalidDateTimeFormatException
     *
     * @param errorMessage message describing the error.
     */
    public InvalidDateTimeFormatException(String errorMessage) {
        super(errorMessage);
    }
}
