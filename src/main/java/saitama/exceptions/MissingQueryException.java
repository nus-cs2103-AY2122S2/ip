package saitama.exceptions;

/**
 * An exception that is thrown when the user does not
 * enter a query when using the find command.
 */
public class MissingQueryException extends SaitamaException {
    public MissingQueryException() {
        super("The find query cannot be empty.");
    }
}
