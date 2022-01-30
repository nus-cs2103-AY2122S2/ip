package Exceptions;

/**
 * <h1>WrongTimeArgumentException</h1>
 * <p>
 * WrongTimeArgumentException is used to raise error when the user inputs time in the wrong format.
 * </p>
 *
 * @author Saravanan Anuja Harish
 */
public class WrongTimeArgumentException extends IllegalCommandException {

    // Stores date string input by user.
    private final String time;

    /**
     * Constructor for WrongTimeExceptionException.
     * @param time input given by user.
     */
    public WrongTimeArgumentException(String time) {
        super(time);
        this.time = time;
    }

    /**
     * gives the string representation of the WrongTimeArgumentException.
     * @return the string representation of the exception object.
     */
    @Override
    public String toString() {
        return "You have input: " + this.time + "\nPlease try with the format HHMM";
    }
}
