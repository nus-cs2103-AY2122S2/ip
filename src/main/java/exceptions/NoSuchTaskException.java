package exceptions;

/**
 * This class encapsulates a "No Such Task" exception, that occurs when trying to access a
 * task that is not in a taskList.
 *
 * @author Ong Han Yang
 */
public class NoSuchTaskException extends Exception {
    /**
     * Constructs a No Such Task Exception.
     *
     * @param message the message to be included in the exception.
     */
    public NoSuchTaskException(String message) {
        super(message);
    }
}
