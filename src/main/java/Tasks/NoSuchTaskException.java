package Tasks;

/**
 * This class encapsulates a "No Such tasks.Task" exception, that occurs when trying to
 * access a task that is not in a tasks.TaskList.
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
