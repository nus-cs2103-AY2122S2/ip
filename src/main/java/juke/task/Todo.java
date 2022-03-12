package juke.task;

/**
 * Basic task with a description.
 */
public class Todo extends Task {
    /**
     * Constructor that initializes task with description.
     *
     * @param description Description.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
        assert getTaskIcon() == TaskType.TODO.getTaskIcon();
    }

    /**
     * Copy constructor for cloning.
     *
     * @param task Task to clone.
     */
    private Todo(Todo task) {
        this(task.description);
        this.status = task.status;
        assert this != task;
    }

    /**
     * Returns a clone of this task.
     *
     * @return Clone of this task.
     */
    @Override
    public Object clone() {
        return new Todo(this);
    }
}
