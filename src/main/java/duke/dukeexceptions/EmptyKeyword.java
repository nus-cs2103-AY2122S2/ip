package duke.dukeexceptions;

/**
 * Thrown when the user did not enter the keyword when the command requires one.
 */
public class EmptyKeyword extends DukeExceptions{
    /**
     * Creates a new EmptyKeyword exception.
     */
    public EmptyKeyword() {
        super("Please enter a keyword for find command!");
    }
}
