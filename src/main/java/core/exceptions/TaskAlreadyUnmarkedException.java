package core.exceptions;

/**
 * Exception indicating that the task is already unmarked.
 */
public class TaskAlreadyUnmarkedException extends DookeException {
    public TaskAlreadyUnmarkedException() {
        super("This task was never complete!");
    }
}
