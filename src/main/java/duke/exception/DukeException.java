package duke.exception;

/**
 * Represents exceptions specific to this application.
 */
public class DukeException extends Exception {
    /**
     * Class constructor.
     *
     * @param errorMessage error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
