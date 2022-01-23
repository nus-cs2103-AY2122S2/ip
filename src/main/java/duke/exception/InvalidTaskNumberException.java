package duke.exception;

public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException() {
        super("Oops! your input number is invalid!");
    }
}
