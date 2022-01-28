package main.java.ari.exception;

/**
 * Signals an error causes by empty message in command
 */
public class EmptyCommandException extends AriException {
    public EmptyCommandException() {
        super("Sorry Master, I did not catch that. Could you repeat?");
    }
}
