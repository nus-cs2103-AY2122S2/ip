package duke.exceptions;

public class InvalidCommandException extends DukeException{
    public InvalidCommandException() {
        super();
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
