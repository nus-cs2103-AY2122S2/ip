package duke.exceptions;

/**
 * Represents an exception which is thrown upon an invalid time input.
 */
public class InvalidTimeException extends DukeException {
    /**
     * Constructs an InvalidTimeException.
     */
    public InvalidTimeException() {
        super("OOPS! Please provide a valid time! "
                + "\nOnly these time formats are accepted:"
                + "\nHH:mm"
                + "\nHHmm");
    }
}
