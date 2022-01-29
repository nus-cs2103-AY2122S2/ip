package duke.exceptions;

public class MissingArgumentException extends DukeException {

    public MissingArgumentException() {
        super("Command has no body! Add details after using command");
    }
}
