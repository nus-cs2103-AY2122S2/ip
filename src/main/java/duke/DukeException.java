package duke;

/**
 * DukeException is used to handle exception in Duke
 *
 * @author brandonrhan
 * @version 0.0.0
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException
     *
     * @param error Error message
     */
    public DukeException(String error) {
        super(error);
    }
}
