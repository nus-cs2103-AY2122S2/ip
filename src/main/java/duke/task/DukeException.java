package duke.task;

/**
 * Represents an <code>Exception</code> that can be thrown by the Duke chatbot class. A <code>DukeException</code>
 * object is represented by its string message.
 */
public abstract class DukeException extends Exception {

    /**
     * Returns a new instance of the <code>DukeException</code> class with the specified message.
     * @param message The exception message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns a String representation of the Exception.
     * @return String representation of the Exception.
     */
    @Override
    public abstract String toString();
}
