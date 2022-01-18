package Exceptions;

public class EmptyTimeException extends DukeException {
    public EmptyTimeException (String type) {
        super("OOPS!!! Time of " + type + " is missing. Please indicate a stipulated time.");
    }
}
