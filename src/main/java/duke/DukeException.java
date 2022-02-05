package duke;

/**
 * Exception class for handling errors in Duke Project
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class DukeException extends Exception {
    protected static final String DID_NOT_UNDERSTAND = "Pardon me, but I did not understand what you said.";

    /**
     * Constructor of DukeException class
     *
     * @param message Error message to be displayed
     */
    public DukeException(String message) {
        super(message);
    }
}
