package exceptions;

public class InvalidTaskException extends DukeException {
    /**
     * Constructor for InvalidTaskParams.
     *
     * @param errMsg error message.
     */
    public InvalidTaskException(String errMsg) {
        super(errMsg);
    }
}
