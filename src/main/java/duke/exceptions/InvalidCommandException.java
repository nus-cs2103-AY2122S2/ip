package duke.exceptions;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("My apologies, but it seems that I do not understand your request.");
    }
}
