package duke.exceptions;

/**
 * This exception is thrown when the date in the command body is in
 * an invalid format
 */
public class InvalidDateException extends DukeException {

    /**
     * Creates an InvalidDateException
     */
    public InvalidDateException() {
        super("Date is invalid! Try yyyy-mm-dd");
    }
}
