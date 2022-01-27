package pikabot.exception;

/**
 * Represents an exception thrown when an invalid Todo task is given by user.
 */
public class TodoException extends PikaBotException {

    /**
     * Constructs a TodoException.
     */
    public TodoException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
