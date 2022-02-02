package exceptions;

/**
 * Exception to represent the case when an invalid command (input) is given
 */
public class InvalidInputException extends Exception{
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
