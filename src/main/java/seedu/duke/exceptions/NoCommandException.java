package seedu.duke.exceptions;

/**
 * Thrown when no valid command keyword was given by user.
 */
public class NoCommandException extends DukeException {
    /**
     * Creates a NoCommandException with a message to tell user to rewrite command.
     */
    public NoCommandException() {
        super("Sorry I don't understand :(");
    }
}