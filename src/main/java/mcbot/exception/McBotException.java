package mcbot.exception;

/**
 * Class to catch exceptions under McBot
 */
public class McBotException extends Exception {

    /**
     * Constructs the McBotException. 
     */
    public McBotException() {
        super();
    }

    /**
     * Constructs the McBotException with a message. 
     * 
     * @param message Message of the error. 
     */
    public McBotException(String message) {
        super(message);
    }

}
