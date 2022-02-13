package juke.exception;

/**
 * Exception to handle an invalid command.
 */
public class JukeInvalidCommandException extends JukeException {
    /**
     * Constructor to initialize exception with a message.
     */
    public JukeInvalidCommandException(String cmd) {
        super(String.format("Invalid command: %s.", cmd));
    }
}
