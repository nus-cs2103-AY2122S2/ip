package duke.exception;

/**
 * Represents an exception in the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class DukeException extends Exception{

    /**
     * Constructor to create custom duke errors.
     *
     * @param errorMessage Error message to be encapsulated by the exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}