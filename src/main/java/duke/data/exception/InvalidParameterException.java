package duke.data.exception;

/**
 * An exception that occurs when an invalid parameter is provided.
 */
public class InvalidParameterException extends DukeException {
    /**
     * Constructs an invalid parameter exception.
     *
     * @param message error message to be passed to the exception object.
     */
    public InvalidParameterException(String message) {
        super(message);
    }
}
