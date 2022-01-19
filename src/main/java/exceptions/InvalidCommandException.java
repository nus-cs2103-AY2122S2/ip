package exceptions;

public class InvalidCommandException extends DukeException {
    /**
     * Constructor for InvalidCommandException.
     */
    public InvalidCommandException() {
        super();
    }

    /**
     * Constructor for BaseException.
     *
     * @param errMsg error message.
     */
    public InvalidCommandException(String errMsg) {
        super(errMsg);
    }
}
