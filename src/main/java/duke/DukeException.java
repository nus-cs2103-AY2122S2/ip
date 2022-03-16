package duke;

/**
 * This is a custom Duke Exception class that calls
 * error when faced with invalid processes in Duke application
 *
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */
public class DukeException extends Exception {
    protected String message;

    /**
     * Sets the message param as the message when encountering DukeException
     *
     * @param msg is the message displayed when handling DukeException
     */
    public DukeException(String msg) {
        message = msg;
    }

    /**
     * Call to obtain the provided message for this DukeException
     *
     * @return message that is provided for DukeException
     */
    public String getMessage() {
        return message;
    }
}
