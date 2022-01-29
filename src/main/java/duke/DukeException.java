package duke;

/**
 * Represents a custom exception that can be caught by Duke.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
