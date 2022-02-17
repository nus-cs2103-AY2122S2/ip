package pikabot.exception;

/**
 * Represents an exception specific to PikaBot application.
 */
public class PikaBotException extends Exception {

    /**
     * Constructs a PikaBotException.
     *
     * @param message Message to be printed when a PikaBotException is thrown.
     */
    public PikaBotException(String message) {
        super(message);
    }
}
