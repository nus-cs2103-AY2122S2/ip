package holobot.exception;

/**
 * Represents an exception class that can be thrown when using HoloBot.
 *
 * @author Terng Yan Long
 */
public class HoloBotException extends RuntimeException {
    /**
     * Instantiates a new instance of HoloBotException.
     *
     * @param errorMessage The error message that is related to the exception.
     */
    public HoloBotException(String errorMessage) {
        super(errorMessage);
    }
}



