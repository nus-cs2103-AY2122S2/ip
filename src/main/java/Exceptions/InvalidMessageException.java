package Exceptions;

public class InvalidMessageException extends DukeException {
    public InvalidMessageException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }
}
