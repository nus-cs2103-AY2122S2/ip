package duke.exception;

/**
 * Main Logic of basic exception for Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs the DukeException objects.
     * This class is to group Duke related exceptions together.
     *
     * @param err error message.
     */
    public DukeException(String err) {
        super(err);
    }
}
