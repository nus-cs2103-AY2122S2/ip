package duke.exceptions;

/**
 * Represents the main exception class that handles errors specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * A constructor for <code>DukeException</code>.
     *
     * @param msg a message to display if exception is called.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
