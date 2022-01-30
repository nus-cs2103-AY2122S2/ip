package duke.tasks;

/**
 * Represents a todo task ask.
 */
public class ToDo extends Task {
    /**
     * Constructs a todo task.
     *
     * @param description Task description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a todo task.
     *
     * @param description Task description.
     * @param isDone If the task has is done or not.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String simpleString() {
        String flag = isDone ? "1" : "0";
        return "T" + flag + description;
    }
}
