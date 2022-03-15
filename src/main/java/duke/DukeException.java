package duke;

/**
 * Custom exception class for Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates a new DukeException
     *
     * @param errorMessage Error message explaining the reason for generating the DukeException.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
