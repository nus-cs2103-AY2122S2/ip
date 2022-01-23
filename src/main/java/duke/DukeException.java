package duke;

/**
 * Exception class for handling errors in duke.Duke Project
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class DukeException extends Exception {

    /**
     * Constructor of duke.DukeException class
     * @param message Error message to be displayed
     */
    public DukeException (String message) {
        super(message);
    }
}
