package duke.exceptions;

/**
 * Represents an exception which is thrown upon an invalid date input.
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructs an InvalidDateException.
     */
    public InvalidDateException() {
        super("OOPS! Please provide a valid date! "
                + "\nOnly these date formats are accepted:"
                + "\nMMM-dd-yyyy"
                + "\ndd/MM/yyyy"
                + "\ndd-MM-yyyy"
                + "\nyyyy/MM/dd"
                + "\nyyyy-MM-dd");
    }
}
