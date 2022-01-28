package mickey.app;

/**
 * Custom exception to handle Mickey exceptions.
 */
public class MickeyException extends Exception {

    /**
     * Constructor.
     *
     * @param msg Error message.
     */
    public MickeyException(String msg) {
        super(msg);
    }
}