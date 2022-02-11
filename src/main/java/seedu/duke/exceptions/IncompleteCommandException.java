package seedu.duke.exceptions;

/**
 * Thrown when input command from user is invalid or wrongly formatted.
 */
public class IncompleteCommandException extends DukeException {
    /**
     * Creates a IncompleteCommandException() with an error message telling user to rewrite command.
     */
    public IncompleteCommandException() {
        super("Hmm the command seems to be wrong - did you tell me the task?");
    }
}

