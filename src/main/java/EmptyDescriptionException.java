public class EmptyDescriptionException extends SaitamaException {
    EmptyDescriptionException() {
        super("OOPS!!! The task description cannot be empty.");
    }
}
