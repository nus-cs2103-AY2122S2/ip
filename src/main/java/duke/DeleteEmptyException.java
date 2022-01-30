package duke;

/**
 * Exception when user attempts to delete a task
 * without providing the index of the task
 */
public class DeleteEmptyException extends DukeException {
    public DeleteEmptyException() {
        super("Index of task required for deletion.");
    }
}