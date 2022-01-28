package karen;

/**
 * To rewrap runtime exception thrown while running Karen with
 * custom messages for user.
 */
public class KarenException extends Exception {
    String message;

    public KarenException(String msg) {
        super(msg);
        this.message = msg;
    }

    /**
     *
     * @return Message from KarenException
     */
    @Override
    public String toString() {
        return this.message;
    }
}
