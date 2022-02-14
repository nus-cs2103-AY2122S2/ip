package duke.exception;

/**
 * Represents the BlankCommandException the Duke program would handle. A <code> BlankCommandException </code>
 * object corresponds to a blank command inputted by a user. Eg, " ".
 */
public class BlankCommandException extends DukeException {

    /**
     * Constructor for BlankCommandException class.
     */
    public BlankCommandException() {
        super("OOPS!!! I'm sorry, but I do not accept empty commands! :-(");
    }
}
