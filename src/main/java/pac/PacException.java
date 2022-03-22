package pac;

/**
 * PacException class to handle exceptions
 */
public class PacException extends Exception{

    /**
     * PacException constructor tskes in String
     * @param message
     */
    public PacException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
