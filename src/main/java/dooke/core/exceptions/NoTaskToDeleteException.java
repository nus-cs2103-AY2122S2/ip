package dooke.core.exceptions;

/**
 * Exception indicating that there is no task to delete.
 * @author s7manth
 * @version 0.3
 */
public class NoTaskToDeleteException extends DookeException {
    public NoTaskToDeleteException() {
        super("No task is there to delete!");
    }
}
