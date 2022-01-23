package exceptions;

/**
 * This class encapsulates an invalid input exception, thrown when some parts of an input is
 * expected but not obtained.
 *
 * @author Ong Han Yang
 */
public class InvalidInputException extends Exception {
    /**
     * Constructs an Invalid Input Exception.
     *
     * @param message the message to be included in the exception.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
