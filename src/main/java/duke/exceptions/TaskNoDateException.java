package duke.exceptions;

/**
 * Custom exception to notify when the selected task does not have a date
 */
public class TaskNoDateException extends RuntimeException {
    /**
     * Creates a TaskNoDateException object with a message.
     *
     * @param msg Message
     *
     */
    public TaskNoDateException(String msg) {
        super(msg);
    }

    /**
     * Creates a TaskNoDateException object.
     */
    public TaskNoDateException() {
        super();
    }
}
