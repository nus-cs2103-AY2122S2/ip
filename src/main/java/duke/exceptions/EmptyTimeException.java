package duke.exceptions;

public class EmptyTimeException extends DukeException {
    public EmptyTimeException (String command) {
        super("OOPS!!! Time of " + command + " is missing. Please indicate a stipulated time.");
    }
}
