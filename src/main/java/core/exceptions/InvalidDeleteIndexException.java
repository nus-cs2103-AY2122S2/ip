package core.exceptions;

public class InvalidDeleteIndexException extends DukeException {
    public InvalidDeleteIndexException() {
        super("The index for deletion is invalid!");
    }
}
