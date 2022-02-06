package duke;

/**
 * Custom Exception for Duke related Errors
 */
public class DukeException extends Exception {

    /**
     * @param err error message.
     */
    public DukeException(String err) {
        super(err);
    }
}
