package meep.exception;


/**
 * Signals an error caused by invalid user input.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructor for class InvalidInputException
     *
     * @param message exception message
     */
    public InvalidInputException(String message) {
        super(message);
    }
}

