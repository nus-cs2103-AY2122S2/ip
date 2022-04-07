package duke.exception;

/**
 * Represents an exception thrown when Event, Todo, Deadline task does not have description.
 */
public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
