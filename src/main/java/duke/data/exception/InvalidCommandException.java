package duke.data.exception;

/**
 * Exception that occurs when an invalid command is provided.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructs an invalid command exception.
     *
     * @param errorMessage error message.
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
