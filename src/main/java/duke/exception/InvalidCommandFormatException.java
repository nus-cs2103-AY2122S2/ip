package duke.exception;

/**
 * Exception for an invalid command.
 */
public class InvalidCommandFormatException extends DukeException {

    /**
     * Constructor for InvalidCommandFormatException.
     * @param errorMessage the invalid command format message.
     */
    public InvalidCommandFormatException(String errorMessage) {
        super(errorMessage);
    }
}
