package duke.exception;

/**
 * An exception class specifically for Duke
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
