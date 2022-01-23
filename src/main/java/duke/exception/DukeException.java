package duke.exception;

/**
 * DukeException representing Exceptions that arise due to interactions with Duke.
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