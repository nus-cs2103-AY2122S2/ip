package duke;

public class Task {
    protected String description;
    protected boolean isDone;

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
     * Returns a String of task description.
     *
     * @return
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
     * @return
     */
    public String toStringForSave() {
        return (isDone ? "# 1 # " : "# 0 # ") + this.description;
    }

    /**
     * Returns a String representation of Task.
     *
     * @return
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + this.description;
    }

}
