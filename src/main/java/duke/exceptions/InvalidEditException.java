package duke.exceptions;

/**
 * Error when a specified edit command is not valid.
 */
public class InvalidEditException extends DukeException{
    private static final String PREFIX = "The edit cannot be made as ";

    public InvalidEditException(String reason) {
        super(PREFIX + reason);
    }
}
