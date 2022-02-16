package duke.exceptions;

/**
 * DukeException is an exception class that creates exceptions that are unique to the Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates a DukeException object, constructor.
     *
     * @param errorMessage Message description of the error.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
