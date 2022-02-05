package duke.task;

import duke.tag.Tag;

/**
 * The type Task.
 */
public class Task {
    /**
     * The Description.
     */
    protected String description;
    /**
     * The Tag.
     */
    protected Tag tag;
    /**
     * The Is done.
     */
    protected boolean isDone;

    /**
     * Instantiates a new Task with tag.
     *
     * @param description the description
     * @param tag         the tag
     */
    public Task(String description, Tag tag) {
        this.description = description;
        this.tag = tag;
        this.isDone = false;
        assert this.description != null : "Task description should not be null";
    }

    /**
     * Gets status icon.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description; // mark done task with X
    }

    /**
     * Mark as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s %s", this.getStatusIcon(), this.tag, this.getDescription());
    }
}
