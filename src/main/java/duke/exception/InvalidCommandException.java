package duke.exception;

/**
 * Represents an exception thrown when command given is not recognised.
 */
public class InvalidCommandException extends DukeException {
    /**
     * @param errorMessage The String that will be printed when Exception is thrown.
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
