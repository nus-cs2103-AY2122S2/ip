package duke;

/**
 * Exception when user attempts to enter an invalid command
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
