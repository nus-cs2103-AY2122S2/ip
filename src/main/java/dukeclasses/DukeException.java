package dukeclasses;

/**
 * Represents a general exception with a fixed error message that is thrown if Duke
 * encounters any form of exceptions while it is running.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException
     */
    public DukeException() {
        super("    Invalid input detected. Please check your input");
    }

}
