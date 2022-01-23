package exceptions;

/**
 * This class encapsulates an invalid input exception, thrown when some parts of an input is expected
 * but not obtained.
 */
public class InvalidInputException extends Exception {
    /**
     * Constructor for the exception.
     * @param message the message to be carried.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
