package istjbot.exception;

/**
 * Encapsulates an exception that is specific to the IstjBot's behaviors.
 */
public class BotException extends Exception {
    /**
     * Constructor of this BotException.
     *
     * @param errorMessage String message about the exception thrown.
     */
    public BotException(String errorMessage) {
        super(errorMessage);
    }
}
