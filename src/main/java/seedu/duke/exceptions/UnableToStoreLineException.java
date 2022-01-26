package seedu.duke.exceptions;

public class UnableToStoreLineException extends DukeException {
    public UnableToStoreLineException() {
        super("Can't seem to store the line");
    }
}