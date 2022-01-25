package sana.exception;

/**
 * This class represents the sana.exception thrown when the command give to Sana is incomplete
 *
 * @author Jan
 * @version 1.0
 */
public class IncompleteCommandException extends Exception {
    /** The message Sana says when the message is incomplete */
    private static final String MESSAGE = "MATE, finish your sentence!";

    public IncompleteCommandException() {
        super(IncompleteCommandException.MESSAGE);
    }

    public String getMessage() {
        return IncompleteCommandException.MESSAGE;
    }
}
