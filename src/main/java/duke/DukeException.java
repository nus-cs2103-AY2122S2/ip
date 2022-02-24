package duke;

/**
 * The DukeException wraps all checked exceptions that is related to
 * erroneous user inputs.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
