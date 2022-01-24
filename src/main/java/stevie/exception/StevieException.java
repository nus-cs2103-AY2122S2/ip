package stevie.exception;

/**
 * The stevie.exception.StevieException wraps all checked exceptions that is related to
 * erroneous user inputs.
 */
public class StevieException extends Exception {
    public StevieException(String message) {
        super(message);
    }
}
