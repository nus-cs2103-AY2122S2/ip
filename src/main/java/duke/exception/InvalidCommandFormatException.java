package duke.exception;

/**
 * Exception for an invalid command.
 */
public class InvalidCommandFormatException extends DukeException {

    public InvalidCommandFormatException(String errorMessage) {
        super(errorMessage);
    }
}
