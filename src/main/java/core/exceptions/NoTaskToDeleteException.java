package core.exceptions;

/**
 * Exception indicating that there is no task to delete.
 */
public class NoTaskToDeleteException extends DookeException {
    public NoTaskToDeleteException() {
        super("No task is there to delete!");
    }
}
