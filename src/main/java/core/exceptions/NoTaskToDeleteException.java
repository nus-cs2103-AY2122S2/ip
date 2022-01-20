package core.exceptions;

public class NoTaskToDeleteException extends DukeException {
    public NoTaskToDeleteException() {
        super("No task is there to delete!");
    }
}
