package duke.exceptions;

public class DukeException extends Exception {
    /**
     * Constructor for BaseException.
     */
    public DukeException() {
        super();
    }

    /**
     * Constructor for BaseException.
     *
     * @param errMsg error message.
     */
    public DukeException(String errMsg) {
        super(errMsg);
    }
}
