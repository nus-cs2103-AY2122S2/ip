package duke.exceptions;

/**
 * Represents a duke exception subclass that handles unknown inputs.
 * E.g., "test".
 */
public class UnknownInputException extends DukeException {
    /**
     * Displays message if this exception is called.
     */
    public UnknownInputException() {
        super("This is an unrecognised command! Please enter another command.");
    }
}
