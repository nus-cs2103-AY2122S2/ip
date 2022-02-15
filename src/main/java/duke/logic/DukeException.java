package duke.logic;

/**
 * Represents an exception specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Makes input message more obnoxious.
     *
     * @param message Message to be obnoxified.
     */
    public DukeException(String message) {
        super(message + "!!!");
    }
}
