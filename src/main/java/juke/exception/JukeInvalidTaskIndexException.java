package juke.exception;

/**
 * Exception to handle an invalid task index.
 */
public class JukeInvalidTaskIndexException extends JukeException {
    /**
     * Constructor to initialize exception with a message.
     */
    public JukeInvalidTaskIndexException() {
        super("Invalid task index.");
    }
}
