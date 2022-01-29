package Exceptions;


/**
 * This file contains the implementation of FaultyTaskNumberException.
 * FaultyTaskNumberException is used to raise error when the user inputs a task number that is not present.
 * @author Saravanan Anuja Harish
 */


public class FaultyTaskNumberException extends IllegalCommandException {

    // Stores the value of task num.
    private final int message;

    /**
     * Constructor for FaultyTaskNumberException.
     * @param message the user input task number.
     */
    public FaultyTaskNumberException(int message) {
        super(String.valueOf(message));
        this.message = message;
    }

    /**
     * gives the string representation of the FaultyTaskNumberException.
     * @return the string representation of the exception object.
     */
    @Override
    public String toString() {
        return "Task num: " + this.message + " isn't present. Type 'list' command to view the task numbers.";
    }

}
