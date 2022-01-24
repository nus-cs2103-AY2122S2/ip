package duke.exception;

public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException() {
        super("Oops! please check your input's argument again!");
    }
}
