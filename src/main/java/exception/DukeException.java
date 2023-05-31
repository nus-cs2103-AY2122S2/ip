package exception;

/**
 * Extends Exception to handle custom errors specific to
 * the duke.Duke bot.
 */
public class DukeException extends Exception {
    protected String message;

    /**
     * Class constructor.
     *
     * @param message Custom string of error message(s)
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }
}
