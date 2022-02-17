package duke.ui;

/**
 * Represents exceptions specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor of DukeException
     *
     * @param error error message.
     */
    public DukeException(String error) {
        super(error);
    }
}
