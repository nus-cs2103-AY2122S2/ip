package main.java.ari.exception;

/**
 * Signals an error that occur in Ari
 */
public class AriException extends IllegalArgumentException {
    public AriException() {
    }

    public AriException(String message) {
        super(message);
    }
}
