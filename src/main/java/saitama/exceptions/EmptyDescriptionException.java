package  saitama.exceptions;

public class EmptyDescriptionException extends SaitamaException {
    public EmptyDescriptionException() {
        super("OOPS!!! The task description cannot be empty.");
    }
}
