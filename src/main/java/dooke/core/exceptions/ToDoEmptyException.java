package dooke.core.exceptions;

/**
 * Exception indicating an empty description for the ToDo object.
 * @author s7manth
 * @version 0.3
 */
public class ToDoEmptyException extends DookeException {
    public ToDoEmptyException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
