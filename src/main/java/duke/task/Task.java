package duke.task;

/**
 * Represents a task. Inherited by specific task classes.
 *
 * @author Peter
 */
public abstract class Task {
    /**
     * Description associated with the task.
     */
    protected String description;

    /**
     * Boolean flag of whether the task is done.
     */
    protected boolean isMarked;

    /**
     * Constructor for a task.
     *
     * @param description Description associated with the task.
     * @param isMarked    Boolean flag of whether the task is done.
     */
    public Task(String description, boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    public void markAsDone() {
        this.isMarked = true;
    }

    public void markAsUndone() {
        this.isMarked = false;
    }

    /**
     * Determines whether the associated description contains a given keyword.
     *
     * @param keyword Keyword that is to be indexed.
     * @return Boolean value of whether the associated description contains a given keyword.
     */
    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Converts the task to a form legible by the storage.
     *
     * @return Data representation of the task.
     */
    public abstract String toData();

    @Override
    public String toString() {
        return "[" + (isMarked ? "X" : " ") + "] " + this.description;
    }
}
