package bob.exception;
/**
 * {@inheritDoc}
 */
public class FileException extends BobException {
    public FileException() {
        super("I couldn't save your tasks... Can you restart me >.<");
    }
}
