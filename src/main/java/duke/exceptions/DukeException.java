package duke.exceptions;

/**
 * A throwable object to indicate that the String provided
 * is an invalid command
 */
public class DukeException extends Exception {

    /**
     * Constructs the DukeException Object
     */
    public DukeException() {
    }

    /**
     * @return a String representation of the error message
     */
    @Override
    public String toString() {
        return "OOPS!!! You gave an invalid command!";
    }

}
