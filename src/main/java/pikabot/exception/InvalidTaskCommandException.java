package pikabot.exception;

/**
 * Represents an exception thrown when an invalid Task is given by user.
 */
public class InvalidTaskCommandException extends PikaBotException {

    /**
     * Constructs an InvalidTaskCommandException.
     */
    public InvalidTaskCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
