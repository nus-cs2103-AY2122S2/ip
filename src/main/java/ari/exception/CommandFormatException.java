package main.java.ari.exception;

/**
 * Signals an error caused by having wrong command format
 */
public class CommandFormatException extends AriException {
    public CommandFormatException() {
    }

    public CommandFormatException(String message) {
        super("Sorry Master, please enter command with following format:\n" + message);
    }
}
