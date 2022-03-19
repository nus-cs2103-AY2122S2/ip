package sana.exception;

/**
 * This class represents the sana.exception thrown when the task is not compatible with
 * the command
 *
 * @author Jan
 * @version 1.0
 */
public class IncompatibleTaskException extends SanaException {
    /** The message Sana says when the message is incomplete */
    private static final String MESSAGE = "This task cannot perform this command!";

    public IncompatibleTaskException() {
        super(IncompatibleTaskException.MESSAGE);
    }

    /**
     * The message Sana says when she receives an incomplete command
     *
     * @return  message
     */
    public String getMessage() {
        return IncompatibleTaskException.MESSAGE;
    }
}
