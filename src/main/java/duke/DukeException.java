package duke;

/**
 * Represents exceptions specific to Duke.
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Constructs an instance of DukeException.
     * @param message A string representing the error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
