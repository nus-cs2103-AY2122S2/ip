package duke.exceptions;

/**
 * Represents an exception which is thrown upon no date given for Event and Deadline tasks.
 */
public class EmptyTimeException extends DukeException {
    /**
     * Constructs an EmptyTimeException.
     */
    public EmptyTimeException (String command) {
        super("OOPS!!! Time of " + command + " is missing. Please indicate a stipulated time.");
    }
}
