package juke.exception;

/**
 * Exception to handle an invalid parameter in a command.
 */
public class JukeInvalidParameterException extends JukeException {
    /**
     * Constructor to initialize exception with a message.
     */
    public JukeInvalidParameterException(String param) {
        super(String.format("Invalid parameter: -%s.", param));
    }
}
