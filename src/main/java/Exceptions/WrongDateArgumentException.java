package Exceptions;

/**
 * This file contains the implementation of WrongDateArgumentException.
 * WrongDateArgumentException is used to raise error when the user inputs date in the wrong format.
 * @author Saravanan Anuja Harish
 */

public class WrongDateArgumentException extends IllegalCommandException {

    // Stores date string input by user.
    private final String date;

    /**
     * Constructor for WrongDateExceptionException.
     * @param date input given by user.
     */
    public WrongDateArgumentException(String date) {
        super(date);
        this.date = date;
    }

    /**
     * gives the string representation of the WrongDateArgumentException.
     * @return the string representation of the exception object.
     */
    @Override
    public String toString() {
        return "You have input: " + this.date + "\nPlease try with the format YYYY-MM-DD";
    }
}
