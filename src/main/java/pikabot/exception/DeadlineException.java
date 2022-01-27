package pikabot.exception;

/**
 * Represents an exception thrown when an invalid Deadline task is given by user.
 */
public class DeadlineException extends PikaBotException {

    /**
     * Constructs a DeadlineException.
     *
     * @param message Message to be printed when DeadlineException is thrown.
     */
    public DeadlineException(String message) {
        super("☹ OOPS!!! " + message);
    }
}

