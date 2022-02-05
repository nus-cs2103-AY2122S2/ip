package duke.dukeexceptions;

/**
 * Thrown when the user did not enter the keyword when the command requires one.
 */
public class EmptyKeywordException extends DukeExceptions {
    /**
     * Creates a new EmptyKeywordException exception.
     */
    public EmptyKeywordException() {
        super("Please enter a keyword for find command!");
    }
}
