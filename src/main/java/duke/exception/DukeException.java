package duke.exception;

/**
 * Represents an exception class that can be thrown when using Duke.
 *
 * @author Terng Yan Long
 */
public class DukeException extends RuntimeException {
    /**
     * Initialises a new instance of DukeException.
     *
     * @param errorMessage The error message that is related to the exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}



