package exceptions;

/**
 * This class encapsulates an exception that is thrown where the user inputs are valid, but it is not
 * an action that can be done, according to the state of the code.
 *
 * @author Ong Han Yang
 */
public class InvalidActionException extends Exception {
    /**
     * Constructs an Invalid Action Exception with a message.
     *
     * @param message the message to be carried by the exception.
     */
    public InvalidActionException(String message) {
        super(message);
    }
}
