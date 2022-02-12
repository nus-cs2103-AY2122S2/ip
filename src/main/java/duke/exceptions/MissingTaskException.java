package duke.exceptions;

/**
 * Error when a specified task does not exist in the task list.
 */
public class MissingTaskException extends DukeException {
    /**
     * Constructor for the exception.
     */
    public MissingTaskException() {
        super("The task you specified does not exist :(");
    }
}
