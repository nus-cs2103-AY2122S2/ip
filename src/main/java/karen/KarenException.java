package karen;

/**
 * To rewrap runtime exception thrown while running Karen with
 * custom messages for user.
 */
public class KarenException extends Exception {
    private final String message;

    /**
     * Constructor for throwing a KarenException
     * @param msg Error message
     */
    public KarenException(String msg) {
        super(msg);
        this.message = msg;
    }

    /**
     * Converts message into a central formatting
     * @return Message from KarenException
     */
    @Override
    public String toString() {
        return this.message;
    }
}
