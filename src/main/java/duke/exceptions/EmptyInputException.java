package duke.exceptions;

/**
 * Represents a duke exception subclass that handles empty inputs.
 * E.g., "".
 */
public class EmptyInputException extends DukeException {
    /**
     * Displays message if this exception is called.
     */
    public EmptyInputException() {
        super("☹ You cannot enter an empty command! Please enter a command.");
    }
}
