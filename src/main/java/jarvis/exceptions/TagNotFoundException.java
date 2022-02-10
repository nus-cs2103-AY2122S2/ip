package jarvis.exceptions;

public class TagNotFoundException extends JarvisException {
    /**
     * Constructor for TagNotFound.
     *
     * @param errMsg error message.
     */
    public TagNotFoundException(String errMsg) {
        super(errMsg);
    }
}
