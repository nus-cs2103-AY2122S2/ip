package spark.exceptions.formatexceptions;

/**
 * This is an exception thrown when a user inputs an invalid command keyword.
 */
public class UnrecognisedCommandException extends FormatException {
    /**
     * Creates an Exception containing the
     * error message to be displayed to the user on the GUI.
     */
    public UnrecognisedCommandException() {
        super("I'm sorry, but I don't know what that means.");
    }
}
