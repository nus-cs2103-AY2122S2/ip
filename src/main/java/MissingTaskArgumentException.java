/**
 * This file contains the implementation of MissingTaskArgumentException.
 * This Exception class throws exception when the task argument is missing in the user input.
 * @author Saravanan Anuja Harish
 */


public class MissingTaskArgumentException extends IllegalCommandException {

    // stores the user input.
    private final String message;

    /**
     * Constructor for MissingTaskArgumentException.
     * @param message the user input
     */
    MissingTaskArgumentException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * gives the string representation of the MissingTaskArgumentException.
     * @return the string representation of the exception object.
     */
    @Override
    public String toString() {
        return "Task missing for :" + this.message;
    }
}
