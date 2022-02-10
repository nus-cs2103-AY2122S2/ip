package duke.exception;

/**
 * An exception class that inherits from DukeException.
 * Will be used when the user tries to access task with index more or less than the current task list's size.
 */
public class DukeOutOfBoundException extends DukeException {
    public DukeOutOfBoundException(String errorMessage) {
        super(errorMessage);
    }
}
