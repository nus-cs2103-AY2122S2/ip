package Exceptions;

/**
 * InvalidMessageException is a class that deals with non-applicable commands
 */
public class InvalidMessageException extends DukeException {
    public InvalidMessageException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
