package exception;

/** Planned exceptions thrown by bot. */
public class DukeException extends Exception {

    /** Constructor helps format error message. */
    public DukeException(String errorMessage) {
        super("OOPS!!! " + errorMessage);
    }
}
