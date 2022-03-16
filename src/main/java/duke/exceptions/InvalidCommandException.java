package duke.exceptions;

/**
 * Represents an exception which is thrown upon an invalid command input.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructs an InvalidCommandException.
     */
    public InvalidCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :(");
    }
}
