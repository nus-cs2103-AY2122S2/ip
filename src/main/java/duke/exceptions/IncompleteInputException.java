package duke.exceptions;

/**
 * Represents a duke exception subclass that handles incomplete inputs.
 * E.g., "deadline".
 */
public class IncompleteInputException extends DukeException {
    /**
     * Displays message if this exception is called.
     *
     * @param input the String to be represented when there is an incomplete command.
     */
    public IncompleteInputException(String input) {
        super(input + " is an incomplete command! Please enter another command.");
    }
}
