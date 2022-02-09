package bernie.exceptions;

/**
 * BernieException is thrown when an invalid command is given
 */
public class BernieException extends Exception {
    public BernieException(String errorMessage) {
        super(errorMessage);
    }
}
