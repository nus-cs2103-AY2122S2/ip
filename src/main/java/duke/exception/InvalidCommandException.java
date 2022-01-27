package duke.exception;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("Sorry, I did not recognize your command.");
    }
}
