package exceptions;

/**
 * A class that belongs to the Exceptions Package.
 * This class encapsulates the message that should be displayed when users parsed an invalid input
 * for the {@link tasks.Tasks} class.
 */
public class TaskException extends Exception {

    /**
     * Constructs TaskException.
     */
    public TaskException() {
        super("OOPS!!!");
    }
}
