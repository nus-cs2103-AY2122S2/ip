package duke.exceptions;

public class InvalidCommandException extends DukeException {

    public InvalidCommandException() {
        super("Command is invalid!");
    }
}
