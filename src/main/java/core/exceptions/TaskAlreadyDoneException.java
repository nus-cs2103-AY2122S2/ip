package core.exceptions;

/**
 * Exception indicating that the task has already been marked as completed.
 */
public class TaskAlreadyDoneException extends DookeException {
    public TaskAlreadyDoneException() {
        super("This task has already been done!");
    }
}
