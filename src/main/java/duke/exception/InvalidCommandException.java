package duke.exception;

/**
 * A specialised exception for invalid/unknown command.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructs the InvalidCommandException class.
     * This exception occurs when the user input format is unknown.
     */
    public InvalidCommandException() {
        super("Sorry, I did not recognize your command.");
    }
}
