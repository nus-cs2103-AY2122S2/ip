package exceptions;

public class DukeToDoEmptyException extends DukeException {

    public DukeToDoEmptyException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
