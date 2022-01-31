package pikabot.exception;

/**
 * Represents an exception thrown when an invalid integer command is given by user.
 */
public class NoIntegerException extends PikaBotException {

    /**
     * Constructs a NoIntegerException.
     *
     * @param message Message to be printed when NoIntegerException is thrown.
     */
    public NoIntegerException(String message) {
        super("☹ OOPS!!! " + message);
    }
}

