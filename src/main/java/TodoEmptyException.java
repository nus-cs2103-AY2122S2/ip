public class TodoEmptyException extends DukeException {
    public TodoEmptyException() {
        super("The description of a todo cannot be empty.");
    }
}
