package duke.exception;

/**
 * An exception class that inherits from DukeException.
 * Will be used when the user's input does not come with a command.
 */
public class DukeCommandDoesNotExistException extends DukeException {
    public DukeCommandDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
