package narcibot;

/**
 * Represents an exception caused due to incorrect format for the required command.
 */
public class IncorrectFormatException extends Exception {
    public IncorrectFormatException (String errorMessage) {
        super(errorMessage);
    }
}
