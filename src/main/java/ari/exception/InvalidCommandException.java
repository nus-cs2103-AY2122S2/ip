package ari.exception;

/**
 * Signals an error that is caused by entering an invalid command
 */
public class InvalidCommandException extends AriException {

    public InvalidCommandException(String message) {
        super(message);
    }

}
