package duke.exceptions;

/**
 * Error when a specified task does not exist in the task list.
 */
public class InvalidTaskException extends DukeException {
    public InvalidTaskException() {
        super("The task you specified does not exist :(");
    }
}
