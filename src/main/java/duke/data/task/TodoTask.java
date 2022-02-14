package duke.data.task;

import duke.data.exception.IllegalValueException;

/**
 * A todo task.
 */
public class TodoTask extends Task {
    /**
     * Constructs a new todo task from the description, completion status and id.
     *
     * @param description description of the task.
     * @param done completion status of the task.
     * @param id id of the task.
     */
    public TodoTask(String description, boolean done, String id, String tag) throws IllegalValueException {
        super(description, done, id, tag);
    }

    /**
     * Constructs a new todo task from the description.
     *
     * @param description description of the task.
     */
    public TodoTask(String description, String tag) throws IllegalValueException {
        super(description, tag);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return string representation of the todo task.
     */
    @Override
    public String toString() {
        if (super.tag == null) {
            return "[T]" + super.toString();
        }
        return "[T]" + super.toString() + " <" + super.getTagName() + ">";
    }
}
