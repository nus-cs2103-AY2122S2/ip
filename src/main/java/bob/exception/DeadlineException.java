package bob.exception;
/**
 * {@inheritDoc}
 */
public class DeadlineException extends BobException {

    /**
     * Constructor of the deadline exception.
     */
    public DeadlineException() {
        super("Adding a deadline requires [task name] + [/by] + [date](yyyy-MM-dd) + [T] + [time](HH:mm)! "
                + "(ノಠ益ಠ)ノ彡┻━┻\n" + "\t[e.g. \"deadline Learn how to add deadline /by 2022-02-22T02:22\"]");
    }
}
