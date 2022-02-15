package duke.exception;

/**
 * DukeException represents exceptions specific to Duke.
 *
 * @author yl-ang
 */
public class DukeException extends RuntimeException {

    /**
     * Constructs a DukeException object with the specified errorMessage.
     *
     * @param errorMessage Error message supplied.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
