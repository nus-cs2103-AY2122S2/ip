package exceptions;

/**
 * A class that belongs to the Exceptions Package.
 * This class encapsulates the message that should be displayed when users parsed an invalid input
 * for the {@link tasks.DeadLines} class.
 */
public class DeadlineException extends TaskException {

    /**
     * Constructor for DeadLineException class.
     */
    public DeadlineException() {
        super();
    }

    /**
     * Message to be displayed when a DeadLineException happens.
     * @return Message as a string.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "The description of a deadline cannot be empty.";
    }
}
