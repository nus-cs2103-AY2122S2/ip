package duke.main;

/**
 * DukeException is an Exception.
 * A custom exception for all exceptions that occur in Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * Extends Exception class. DukeException can catch all kinds of Exceptions.
     *
     * @param msg the exception message to be shown
     */
    public DukeException(String msg) {
        super(msg);
    }

    /**
     * Overriden toString method.
     * Returns the message.
     * @return
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
