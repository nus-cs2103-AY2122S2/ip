package pikabot.exception;

/**
 * Represents an exception thrown when an invalid Event task is given by user.
 */
public class EventException extends PikaBotException {

    /**
     * Constructs an EventException.
     *
     * @param message Message to be printed when EventException is thrown.
     */
    public EventException(String message) {
        super("☹ OOPS!!! " + message);
    }
}


