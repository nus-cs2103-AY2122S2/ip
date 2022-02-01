package duke;

/**
 * This is a custom duke.Duke Exception class
 *
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class DukeException extends Exception {
    protected String message;

    public DukeException(String msg) {
        message = msg;
    }

    public String getMessage() {
        return message;
    }
}
