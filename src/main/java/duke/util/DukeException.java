package duke.util;

/**
 * Represents the exception specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Class constructor.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
