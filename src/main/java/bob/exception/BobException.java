package bob.exception;

/**
 * Represents the exception that gives Bob the message to say when an error occurs.
 */
public class BobException extends IllegalArgumentException {
    public BobException(String error) {
        super(error);
    }
    public String getBobReply() {
        return getMessage();
    }
}
