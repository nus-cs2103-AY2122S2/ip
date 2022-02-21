package duke;

/**
 * Thrown to indicate that an error has occurred in Duke.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
