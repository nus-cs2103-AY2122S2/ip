package duke.exceptions;

/**
 * Representing a Deadline exception.
 */
public class DeadlineException extends DukeException {
    public DeadlineException() {
        super("Invalid format for deadline. \n" +
                "Correct format: deadline <name> /by <YYYY-MM-DD> <HHMM>\n" +
                "Example: deadline read book /by 2012-03-04 1234\n");
    }
}
