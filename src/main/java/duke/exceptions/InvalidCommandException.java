package duke.exceptions;

public class InvalidCommandException extends DukeException {
    /**
     * Constructor for BaseException.
     *
     * @param errMsg error message.
     */
    public InvalidCommandException(String errMsg) {
        super(errMsg);
    }
}
