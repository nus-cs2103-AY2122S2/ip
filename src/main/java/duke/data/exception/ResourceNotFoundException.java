package duke.data.exception;

/**
 * An exception that occurs when the requested resource cannot be located.
 */
public class ResourceNotFoundException extends DukeException {
    /**
     * Constructs an exception with the given error message.
     *
     * @param errorMessage error message to be passed to the exception object.
     */
    public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
