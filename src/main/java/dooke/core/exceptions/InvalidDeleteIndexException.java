package dooke.core.exceptions;

/**
 * Exception indicating an improper index argument passed to the delete command.
 * @author s7manth
 * @version 0.3
 */
public class InvalidDeleteIndexException extends DookeException {
    public InvalidDeleteIndexException() {
        super("The index for deletion is invalid!");
    }
}
