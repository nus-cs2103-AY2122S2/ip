package duke.exception;

public class IncompleteCommandException extends DukeException {
    public IncompleteCommandException(String command) {
        super("OOPS!!! The description of a " + command + " cannot be empty.");
    }
}
