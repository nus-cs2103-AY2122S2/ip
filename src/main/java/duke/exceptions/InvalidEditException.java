package duke.exceptions;

/**
 * Error when a specified edit command is not valid.
 */
public class InvalidEditException extends DukeException {
    private static final String PREFIX = "The edit cannot be made as ";

    /**
     * Constructor for the exception.
     *
     * @param reason the reason why the edit command cannot be executed
     */
    public InvalidEditException(String reason) {
        super(PREFIX + reason);
    }
}
