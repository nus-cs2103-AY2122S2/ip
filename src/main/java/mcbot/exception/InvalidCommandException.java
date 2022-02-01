package mcbot.exception;

/**
 * Class to catch invalid commands when parsed in McBot 
 */
public class InvalidCommandException extends McBotException {

    /**
     * Constructs the InvalidCommandException.
     */
    public InvalidCommandException() {
        super();
    }

    /**
     * Constructs the InvalidCommandException with a message.
     * 
     * @param message Message of the error.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
    
}
