package dooke.core.exceptions;

/**
 * Exception indicating that the task has already been marked as completed.
 * @author s7manth
 * @version 0.2
 */
public class TaskAlreadyDoneException extends DookeException {
    public TaskAlreadyDoneException() {
        super("This task has already been done!");
    }
}
