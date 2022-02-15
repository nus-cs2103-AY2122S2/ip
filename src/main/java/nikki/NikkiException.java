package nikki;

/**
 * Exception class for exceptions strictly within the application.
 * These exceptions should always be handled.
 * The error message should be properly displayed to the user.
 */
public class NikkiException extends Exception {
    public NikkiException(String errorMessage) {
        super(errorMessage);
    }
}
