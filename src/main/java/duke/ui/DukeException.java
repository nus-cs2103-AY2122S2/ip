package duke.ui;

/**
 * An exception class specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException object that
     * displays the parameter message.
     * @param message error message
     */
    public DukeException(String message) {
        super(message);
    }
}