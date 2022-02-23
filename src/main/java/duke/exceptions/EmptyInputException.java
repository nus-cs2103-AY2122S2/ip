package duke.exceptions;

/**
 * Exception when the input is empty.
 */
public class EmptyInputException extends Exception{
    /**
     * Construct an EmptyInputException.
     * @param errorMessage
     */
    public EmptyInputException(String errorMessage) {
        super(errorMessage);
    }
}
