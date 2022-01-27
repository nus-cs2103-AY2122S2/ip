package duke;

public class TodoEmptyException extends DukeException {
    /**
     * Returns a duke.TodoEmptyException if an empty TODO event
     * is entered by the user
     *
     * @author  Ryan Aidan
     */
    public TodoEmptyException() {
        super("The description of a todo cannot be empty.");
    }
}
