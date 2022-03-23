package duke.exceptions;

/**
 * Signals that an error has occurred within Duke.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
