package exceptions;

/** A subclass of Exception, to represent exceptions specific to Duke. */
public class DukeException extends Exception {

    /**
     * Constructs a new exception with an error message.
     *
     * @param errorMessage Error message of the error thrown.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
