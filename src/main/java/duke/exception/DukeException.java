package duke.exception;

public class DukeException extends RuntimeException {
    /**
     * Initialises a new instance of DukeException.
     *
     * @param errorMessage The error message that is related to the exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}



