package duke;

/**
 * Represents exception when description of task is empty
 */
public class EmptyDescriptionException extends DukeException {
    EmptyDescriptionException(String message) {
        super(message);
    }
}
