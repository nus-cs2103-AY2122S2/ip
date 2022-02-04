package jarvis.exceptions;

public class InvalidCommandException extends JarvisException {
    /**
     * Constructor for BaseException.
     *
     * @param errMsg error message.
     */
    public InvalidCommandException(String errMsg) {
        super(errMsg);
    }
}
