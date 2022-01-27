package duke.exception;

/**
 * Signals an issue with the execution of Duke.
 */
public class DukeException extends Exception {
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}
