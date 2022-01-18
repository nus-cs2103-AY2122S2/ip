/**
 * This class encapsulates a "No Such Task" exception, that occurs when trying to
 * access a task that is not in a TaskList.
 */
public class NoSuchTaskException extends Exception {
    /**
     * Constructor.
     * @param message the message to be included in the exception.
     */
    public NoSuchTaskException(String message) {
        super(message);
    }
}
