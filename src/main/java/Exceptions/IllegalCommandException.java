package Exceptions;

/**
 * This file contains the implementation of IllegalCommandException.
 * IllegalCommandException is used to raise an error when invalid arguments are fed.
 * @author Saravanan Anuja Harish
 */

public class IllegalCommandException extends IllegalArgumentException {

    /**
     * Constructor for IllegalCommandException.
     * @param message the user input.
     */
    public IllegalCommandException(String message) {
        super(message);
    }

    /**
     * gives the string representation of the IllegalCommandException.
     * @return the string representation of the exception object.
     */
    @Override
    public String toString() {
        return "IllegalCommandException: " + getMessage() + ". Please enter a valid command!";
    }
}
