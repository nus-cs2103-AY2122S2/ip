package seedu.duke.exceptions;

public class UnableToUpdateDatabaseException extends DukeException {
    public UnableToUpdateDatabaseException() {
        super("Can't seem to store the line");
    }
}