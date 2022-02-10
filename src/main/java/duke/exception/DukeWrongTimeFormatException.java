package duke.exception;

/**
 * An exception class that inherits from DukeException.
 * Will be used when the user's time input for a task has a wrong format.
 */
public class DukeWrongTimeFormatException extends DukeException {
    public DukeWrongTimeFormatException(String errorMessage) {
        super(errorMessage);
    }
}
