package duke.exception;

/**
 * Represents an exception that is thrown by Duke. A
 * <code>DukeException</code> object can be created and thrown to
 * specify the type of duke exception error occurring in the program.
 */
public class DukeException extends Exception {
    public final boolean isHidden;
    public final boolean isInvalidCommand;

    /**
     * Creates an instance of a DukeException object.
     *
     * @param errorMsg the error message generated.
     */
    public DukeException(String errorMsg) {
        this(errorMsg, false);
    }

    /**
     * Creates an instance of a DukeException object.
     *
     * @param errorMsg the error message generated.
     * @param isHidden whether to hide the error message.
     */
    public DukeException(String errorMsg, boolean isHidden) {
        this(errorMsg, isHidden, false);
    }

    /**
     * Creates an instance of a DukeException object.
     *
     * @param errorMsg the error message generated.
     * @param isHidden whether to hide the error message.
     * @param isInvalidCommand whether the error is due to an invalid command.
     */
    public DukeException(String errorMsg, boolean isHidden, boolean isInvalidCommand) {
        super(errorMsg);
        this.isHidden = isHidden;
        this.isInvalidCommand = isInvalidCommand;
    }
}
