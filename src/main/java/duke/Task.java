package duke;

/**
 * Represents user task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Tag tag;

    /**
     * Constructs a Task object.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task object.
     *
     * @param description
     * @param tag
     */
    public Task(String description, Tag tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    /**
     * Returns a String of task description.
     *
     * @return String of task description.
     */
    public String getDescription() {
        return this.description;
    }

    /** Marks task as done*/
    public void markAsDone() {
        this.isDone = true;
    }

    /** Marks task as not done*/
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a String when save.
     *
     * @return String to save to disk.
     */
    public String toStringForSave() {
        return (isDone ? "# 1 # " : "# 0 # ") + this.description;
    }

    /**
     * Returns a String representation of Task.
     *
     * @return String that represent the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[✅] " : "[ ] ") + this.description;
    }

    /**
     * Tags a Task.
     *
     * @param taskTag A task tag.
     * @return Task object with the respective tag.
     */
    public Task tag(Tag taskTag) {
        return new Task(description, taskTag);
    }

}
