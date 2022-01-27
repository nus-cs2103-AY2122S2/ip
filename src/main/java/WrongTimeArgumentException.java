/**
 * This file contains the implementation of WrongTimeArgumentException.
 * WrongTimeArgumentException is used to raise error when the user inputs time in the wrong format.
 * @author Saravanan Anuja Harish
 */

public class WrongTimeArgumentException extends IllegalCommandException {

    // Stores date string input by user.
    private final String time;

    /**
     * Constructor for WrongTimeExceptionException.
     * @param time input given by user.
     */
    WrongTimeArgumentException(String time) {
        super(time);
        this.time = time;
    }

    /**
     * gives the string representation of the WrongTimeArgumentException.
     * @return the string representation of the exception object.
     */
    @Override
    public String toString() {
        return "You have input: " + this.time + "\n please try with the format HHMM";
    }
}
