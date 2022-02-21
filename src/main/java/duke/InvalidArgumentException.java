package duke;

/**
 * Thrown when a user input cannot be parsed due to being invalid or incorrectly formatted.
 */
public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
