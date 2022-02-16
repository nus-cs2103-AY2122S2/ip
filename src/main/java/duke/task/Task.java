package duke.task;

/**
 * Represents a general task.
 */
public abstract class Task {
    private boolean isDone;
    private String description;
    private Tag tag;

    /**
     * A Task constructor to initialise a <code>Task</code> object. A <code>Task</code>
     * corresponds to a task represented by a String.
     * E.g., <code>do project</code>.
     * However, this is an abstract class so Task cannot be instantiated.
     *
     * @param description the description of the task to be done.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * An overloaded Task constructor to initialise a <code>Task</code> object. A <code>Task</code>
     * corresponds to a task represented by a String and a Tag.
     * E.g., <code>do project</code>.
     * However, this is an abstract class so Task cannot be instantiated.
     *
     * @param description the description of the task to be done.
     * @param tag the tag of the task to be added.
     */
    public Task(String description, Tag tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the attribute of the task to be true.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Sets the attribute of the task to be false.
     */
    public void unmark() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    /**
     * Returns the string representation of the <code>Task</code> task.
     *
     * @return the string representation of the <code>Task</code> task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
