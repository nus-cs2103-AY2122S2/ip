package seedu.task;

/**
 * The todo class, a subclass of Task
 */
public class Todo extends Task {

    private static final String TYPE = "T";

    /**
     * Todo constructor
     *
     * @param description Description of the task
     */
    public Todo(String description) {
        super(description, TYPE);
    }

    /**
     * Overloaded constructor with more parameters added
     *
     * @param description Description of the task
     * @param isCompleted Whether the task is completed
     * @param priority The priority level of the task
     */
    public Todo(String description, boolean isCompleted, int priority) {
        super(description, isCompleted, priority, TYPE);
    }
}
