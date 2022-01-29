package Exceptions;

/**
 * This file contains the implementation of EmptyCommandException.
 * EmptyCommandException is used to raise error when the user inputs an empty string or gives no input.
 * @author Saravanan Anuja Harish
 */

public class EmptyCommandException extends IllegalCommandException {


    /**
     * Constructor for EmptyCommandException.
     * @param message the user input
     */
    public EmptyCommandException(String message) {
        super(message);
    }

    /**
     * gives the string representation of the EmptyCommandException.
     * @return the string representation of the exception object.
     */
    @Override
    public String toString() {
        return "You have not input any command, please add some and try again.";
    }

}
