package duke.exceptions;

/**
 * Exception when the parser fail to parse the input.
 */
public class UnknownInputException extends Exception{
    /**
     * Construct an UnknownInputException.
     * @param errorMessage
     */
    public UnknownInputException(String errorMessage) {
        super(errorMessage);
    }
}
