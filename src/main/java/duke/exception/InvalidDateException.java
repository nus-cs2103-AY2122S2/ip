package duke.exception;

/**
 * Exception for invalid date.
 */
public class InvalidDateException extends DukeException {

    /**
     * Constructor for InvalidDateException.
     */
    public InvalidDateException() {
        super("Invalid date format -e.g, deadline homework /by yyyy-mm-dd");
    }
}
