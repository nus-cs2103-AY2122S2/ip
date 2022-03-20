package dooke.core.exceptions;

/**
 * Exception indicating that the task is already unmarked.
 * @author s7manth
 * @version 0.3
 */
public class TaskAlreadyUnmarkedException extends DookeException {
    public TaskAlreadyUnmarkedException() {
        super("This task was never complete!");
    }
}
