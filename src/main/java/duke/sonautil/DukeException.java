package duke.sonautil;

/**
 * Represents the exception thrown when user's command is invalid
 */
public class DukeException extends Exception {
    public DukeException(String s) {
        super(s);
    }
}
