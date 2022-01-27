package duke.task;

/**
 * Represents an exception caused by attempting to create a <code>ToDo</code> object with invalid or incomplete
 * arguments. A <code>ToDoIllegalArgumentException</code> is represented by an error message.
 */
public class ToDoIllegalArgumentException extends IllegalArgumentException {
    /**
     * Returns a new instance of <code>ToDoIllegalArgumentException</code> with the specified Exception message.
     * @param message The error message of the exception.
     */
    public ToDoIllegalArgumentException(String message) {
        super(message);
    }

    /**
     * Returns a String representation of the exception.
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return "UH-OH!! you gotta fill in the description to create a valid todo (> <ლ)";
    }
}