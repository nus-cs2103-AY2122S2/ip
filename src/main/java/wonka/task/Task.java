package wonka.task;

/**
 * Represents a task to be done. This task possesses a state/status that is by default not done. Task also has a
 * tracker that keeps track of the type of task.
 */
public abstract class Task {
    private static final String DONE_STATUS = "[X]";
    private static final String UNDONE_STATUS = "[ ]";
    private String status = UNDONE_STATUS; // for all new tasks added to list, they are initially not done.
    private final String description;
    private boolean isDone = false;

    /**
     * Creates a Task that is initially undone.
     *
     * @param description Description of Task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        if (!isDone) {
            isDone = true;
            this.status = DONE_STATUS;
        }
    }

    /**
     * Marks the task as undone.
     */
    public void unmark() {
        if (isDone) {
            isDone = false;
            this.status = UNDONE_STATUS;
        }
    }

    /**
     * Returns the current status of the Task.
     *
     * @return Current position of Task.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Returns the type of Task.
     *
     * @return Type of Task.
     */
    public abstract String track();

    /**
     * Returns the name of the Task.
     *
     * @return Name of Task.
     */
    public abstract String getName();

    /**
     * Returns the String representation of a Task
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return this.description;
    }

}
