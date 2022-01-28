package core.exceptions;

/**
 * Exception indicating an empty description for the ToDo object.
 */
public class ToDoEmptyException extends DookeException {
    public ToDoEmptyException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
