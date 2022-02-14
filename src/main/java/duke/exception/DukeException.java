package duke.exception;

/**
 * Represents the Exceptions the Duke program would handle. A <code> DukeException </code> object corresponds
 * to the exceptions specified and handled by Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException class.
     * @param description the error message provided for the exception.
     */
    public DukeException(String description) {
        super(description);
    }
}
