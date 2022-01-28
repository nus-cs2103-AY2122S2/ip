package duke.exception;

/**
 * Exception for invalid date.
 */
public class InvalidDateException extends DukeException {

    public InvalidDateException() {
        super("Invalid date format -e.g, deadline homework /by yyyy-dd-mm");
    }
}
