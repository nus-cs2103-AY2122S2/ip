package duke.exceptions;

public class DukeUnknownCommandException extends DukeException {

    public DukeUnknownCommandException() {
        super("OOPS! This is an incorrect type of instruction");
    }
}
