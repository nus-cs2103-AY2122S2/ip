package duke.tasks;

import duke.exception.DukeException;

/**
 * Inherits from Task and is the implementation of a simple Todo.
 */
public class ToDo extends Task {

    /**
     * Constructor for the todo task.
     *
     * @param task the description of the task
     * @throws DukeException if the description is empty
     */
    public ToDo(String task) throws DukeException {
        super(task);
        this.initials = "T";
        if (task.length() < 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
        }
    }

    /**
     * Converts the Todo object into a readable form.
     *
     * @return String representation of Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

