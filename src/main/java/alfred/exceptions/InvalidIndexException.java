package alfred.exceptions;

/**
 * Encapsulates the exception thrown when an index is used to reference
 * an item in the list that is out of valid bounds.
 */
public class InvalidIndexException extends AlfredException {
    static String ERROR_MESSAGE = "Invalid input, sir. Likely due to an invalid list item.";

    public InvalidIndexException() {
        super(InvalidIndexException.ERROR_MESSAGE);
    }
}