package pikabot.exception;

/**
 * Represents an exception thrown when an invalid keyword to search for is given by user.
 */
public class FindException extends PikaBotException {

    /**
     * Constructs a FindException.
     */
    public FindException() {
        super("Please enter a keyword to search for!");
    }
}
