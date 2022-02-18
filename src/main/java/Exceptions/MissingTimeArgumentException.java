package exceptions;

/**
 * <h1>MissingTimeArgumentException</h1>
 * <p>
 * MissingTimeArgumentException is used to raise error when the time argument of deadline
 * and event tasks are not specified in user input.
 * </p>
 *
 * @author Saravanan Anuja Harish
 */
public class MissingTimeArgumentException extends MissingTaskArgumentException {

    // stores the user message.
    private final String message;

    /**
     * Constructor for MissingTimeArgumentException.
     *
     * @param message the user input.
     */
    public MissingTimeArgumentException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * gives the string representation of the MissingTimeArgumentException.
     *
     * @return the string representation of the exception object.
     */
    @Override
    public String toString() {
        return "Time argument missing for: " + this.message;
    }
}
