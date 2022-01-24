package duke.exception;

/**
 * Represents an exception that is thrown by Duke specific to invalid commands.
 * A <code>InvalidArgumentException</code> object can be created and thrown to
 * specify the type of duke exception error due to invalid commands
 * occurring in the program.
 */
public class InvalidArgumentException extends DukeException {

    /**
     * Creates a default instance of a InvalidArgumentException object.
     */
    public InvalidArgumentException() {
        super("", false, true);
    }

    /**
     * Creates an instance of a InvalidArgumentException object.
     *
     * @param errorMsg the error message generated.
     */
    public InvalidArgumentException(String errorMsg) {
        super(errorMsg, false, true);
    }

    /**
     * Creates an instance of a InvalidArgumentException object.
     *
     * @param errorMsg the error message generated.
     * @param isHidden whether to hide the error message.
     */
    public InvalidArgumentException(String errorMsg, boolean isHidden) {
        super(errorMsg, isHidden, true);
    }


}
