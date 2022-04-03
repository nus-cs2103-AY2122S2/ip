package duke.exceptions;

/**
 * Exception when no description os passed into the input.
 */
public class EmptyDescriptionException extends Exception{
    /**
     * Construct an EmptyDescriptionException.
     * @param errorMessage
     */
    public EmptyDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
