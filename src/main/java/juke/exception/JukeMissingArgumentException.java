package juke.exception;

/**
 * Exception to handle a missing argument in a command.
 */
public class JukeMissingArgumentException extends JukeException {
    /**
     * Constructor to initialize exception with a message.
     */
    public JukeMissingArgumentException(String cmd) {
        super(String.format("Missing argument for command %s.", cmd));
    }
}
