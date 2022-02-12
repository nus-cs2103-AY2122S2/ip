package seedu.duke.exceptions;

public class InvalidNoteIndexException extends DukeException {
    public InvalidNoteIndexException() {
        super("The note doesn't seem to exist");
    }
}

