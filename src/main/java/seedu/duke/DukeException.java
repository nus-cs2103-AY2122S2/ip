package seedu.duke;

/**
 * Exception class for Duke
 */
public class DukeException extends Exception {

    /**
     * Constructor
     *
     * @param errorMessage The message for the error that the application has caught
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
