package duke;

/**
 * Exception when user attempts to create a todo task
 * without providing a task name/description
 */
public class TodoEmptyException extends DukeException {
    public TodoEmptyException() {
        super("The description of a todo cannot be empty.");
    }
}