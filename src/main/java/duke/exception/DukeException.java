package duke.exception;

/**
 * Represents an exception that happens when the user interacts with Duke.
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException with the given message.
     *
     * @param message Exception message.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
