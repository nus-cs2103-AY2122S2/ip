package alfred.exceptions;

/**
 * Encapsulates an exception that occurs when an invalid command keyword
 * is passed to Alfred.
 */
public class InvalidCommandException extends AlfredException {
    static String ERROR_MESSAGE = "Invalid command, sir. I'm not sure what you want me to do.";

    public InvalidCommandException() {
        super(InvalidCommandException.ERROR_MESSAGE);
    }
}