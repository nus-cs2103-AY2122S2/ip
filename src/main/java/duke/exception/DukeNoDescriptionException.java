package duke.exception;

/**
 * An exception class that inherits from DukeException.
 * Will be used when the user's input does not come with a description of a task.
 */
public class DukeNoDescriptionException extends DukeException {
    public DukeNoDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
