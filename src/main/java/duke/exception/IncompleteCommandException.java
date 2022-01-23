package duke.exception;

/**
 * Represents the IncompleteCommandException the Duke program would handle. A <code> IncompleteCommandException </code>
 * object corresponds to a incomplete command inputted by a user. Eg, event sleep /at.
 */
public class IncompleteCommandException extends DukeException {

    /**
     * Constructor for IncompleteCommandException class.
     * @param command the respective task the user has inputted eg,todo/event/deadline.
     */
    public IncompleteCommandException(String command) {
        super("OOPS!!! The description of a " + command + " cannot be empty.");
    }
}
