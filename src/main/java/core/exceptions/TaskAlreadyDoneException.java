package core.exceptions;

public class TaskAlreadyDoneException extends DukeException {
    public TaskAlreadyDoneException() {
        super("This task has already been done!");
    }
}
