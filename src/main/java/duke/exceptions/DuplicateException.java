package duke.exceptions;

/**
 * Representing when there are duplicate names.
 */
public class DuplicateException extends DukeException {
    public DuplicateException(String message) {
        super("Duke already has a task with that name!");
    }
}
