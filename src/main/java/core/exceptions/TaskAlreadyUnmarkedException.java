package core.exceptions;

public class TaskAlreadyUnmarkedException extends DukeException {
    public TaskAlreadyUnmarkedException() {
        super("This task was never complete!");
    }
}
