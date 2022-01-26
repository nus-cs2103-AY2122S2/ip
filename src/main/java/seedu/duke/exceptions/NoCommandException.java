package seedu.duke.exceptions;

public class NoCommandException extends DukeException {
    public NoCommandException() {
        super("Sorry I don't understand :(");
    }
}