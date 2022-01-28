package core.exceptions;

/**
 * Exception indicating an improper index argument passed to the delete command.
 */
public class InvalidDeleteIndexException extends DookeException {
    public InvalidDeleteIndexException() {
        super("The index for deletion is invalid!");
    }
}
