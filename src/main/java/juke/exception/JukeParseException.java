package juke.exception;

/**
 * Exception to handle an error in parsing data.
 */
public class JukeParseException extends JukeException {
    /**
     * Constructor to initialize exception with a cause of error.
     *
     * @param cause Cause of error.
     */
    public JukeParseException(String cause) {
        super(String.format("Error with parsing %s.", cause));
    }
}
