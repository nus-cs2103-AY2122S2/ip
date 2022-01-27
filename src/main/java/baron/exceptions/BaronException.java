package baron.exceptions;

/**
 * Deals with all the exceptions in Baron application.
 */
public class BaronException extends Exception {

    /**
     * Constructs a {@code BaronException} with the given message.
     */
    public BaronException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of {@code BaronException}.
     *
     * @return the string representation of {@code BaronException}.
     */
    public String toString() {
        return "☹ OOPS!!! " + getMessage();
    }
}
