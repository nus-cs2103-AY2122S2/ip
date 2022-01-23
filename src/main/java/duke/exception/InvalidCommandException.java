package duke.exception;

/**
 * Represents the InvalidCommandException the Duke program would handle. A <code> InvalidCommandException </code>
 * object corresponds to an invalid command inputted by a user. Eg, blah.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructor for InvalidCommandException class.
     */
    public InvalidCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
