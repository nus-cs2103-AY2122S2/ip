package bob.exception;
/**
 * {@inheritDoc}
 */
public class InvalidIndexException extends BobException {

    /**
     * Constructor for an invalid index exception.
     */
    public InvalidIndexException() {
        super("You need to give me a valid task number! (ಥ﹏ಥ) \n"
                + "\tTry \"list\" if you want to see your tasks and their numbers.");
    }
}
