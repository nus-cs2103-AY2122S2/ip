package spark.exceptions.formatexceptions;

/**
 * This is an exception thrown when a user inputs an invalid command keyword.
 */
public class UnrecognisedCommandException extends FormatException {
    public UnrecognisedCommandException() {
        super("I'm sorry, but I don't know what that means.");
    }
}
