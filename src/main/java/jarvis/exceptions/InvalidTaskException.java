package jarvis.exceptions;

public class InvalidTaskException extends JarvisException {
    /**
     * Constructor for InvalidTaskParams.
     *
     * @param errMsg error message.
     */
    public InvalidTaskException(String errMsg) {
        super(errMsg);
    }
}
