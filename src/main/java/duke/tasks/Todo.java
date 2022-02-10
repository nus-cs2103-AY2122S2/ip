package duke.tasks;

import duke.exceptions.DukeException;

/**
 * Represents a common task that needs to be done.
 * Stores the description of the task.
 */
public class Todo extends Task {

    /**
     * Creates an instance of a Todo object.
     *
     * @param description the details of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * The factory function of the todo task. Throws an exception
     * if the provided tokens does not satisfy the required format to create
     * a todo task.
     *
     * @param tokens the specification of the todo task which contains
     *               the description of the task.
     */
    protected static Todo createTask(String[] tokens) throws DukeException {
        String description = createDescription(tokens);
        if (description.equals("")) {
            throw new DukeException("The description of a todo task cannot be empty!");
        }
        return new Todo(description);
    }

    private static String createDescription(String[] tokens) {
        String description = "";
        for (String token : tokens) {
            if (token.equals("todo")) {
                continue;
            } else {
                description += token + " ";
            }
        }
        return description.trim();
    }
}
