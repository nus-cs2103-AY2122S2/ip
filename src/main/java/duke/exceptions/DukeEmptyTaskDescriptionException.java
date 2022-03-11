package duke.exceptions;

public class DukeEmptyTaskDescriptionException extends DukeException {

    public DukeEmptyTaskDescriptionException() {
        super("OOPS! The description of a task cannot be empty");
    }
}
