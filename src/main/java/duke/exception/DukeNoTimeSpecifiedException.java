package duke.exception;

/**
 * An exception class that inherits from DukeException.
 * Will be used when the user's input does not come with a time for a task.
 */
public class DukeNoTimeSpecifiedException extends DukeException {
    public DukeNoTimeSpecifiedException(String errorMessage) {
        super(errorMessage);
    }
}
