package juke.exception;

/**
 * Exception to handle an empty task list.
 */
public class JukeEmptyTaskListException extends JukeException {
    /**
     * Constructor to initialize exception with a message.
     */
    public JukeEmptyTaskListException() {
        super("Task list is empty.");
    }
}
