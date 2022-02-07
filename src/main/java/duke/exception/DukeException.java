package duke.exception;

/**
 * Represents a Duke exception.
 */
public class DukeException extends Exception {

    /**
     * Constructor for a Duke exception.
     *
     * @param message the error message that wants to be displayed.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the error message.
     *
     * @return the error message.
     */
    @Override
    public String toString() {
        return getMessage();
    }
}
