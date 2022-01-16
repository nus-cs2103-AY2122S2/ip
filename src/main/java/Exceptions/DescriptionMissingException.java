package Exceptions;

/**
 * Exception that occurs when users does not give a description when adding a todo.
 */
public class TodoException extends DukeException {
    public TodoException(String message) {
        super(message);
    }
}
