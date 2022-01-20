package core.exceptions;

public class ToDoEmptyException extends DukeException {
    public ToDoEmptyException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
