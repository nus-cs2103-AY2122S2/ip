package duke.data.exception;

/**
 * Exception that occurs when an invalid value is provided.
 */
public class IllegalValueException extends DukeException {
    /**
     * Constructs an invalid value exception.
     *
     * @param errorMessage error message.
     */
    public IllegalValueException(String errorMessage) {
        super(errorMessage);
    }
}
