package duke.exception;

/**
 * A class that handles any exceptions during the program execution.
 */
public class DukeException extends Exception {
    /**
     * Constructor to initialize and instance of DukeException class
     * with error message.
     *
     * @param errorMessage Error message of the exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
