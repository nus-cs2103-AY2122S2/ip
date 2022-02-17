package duke;

/**
 * Represents a exception where the task description is not provided
 */
public class EmptyDescriptionException extends DukeException {

    EmptyDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "EmptyDescriptionException: " + this.getMessage();
    }
}
