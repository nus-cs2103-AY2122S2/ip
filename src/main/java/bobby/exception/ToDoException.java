package bobby.exception;
/**
 * Represents a 'todo' exception
 */
public class ToDoException extends BobbyException {
    /**
     * Constructor for ToDoException
     *
     * @param message short message to indicate error type.
     */
    public ToDoException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "There is no description after the todo command :(";
    }
}
