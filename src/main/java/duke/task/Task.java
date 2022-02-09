package duke.task;

/**
 * Task class. Is extended by Deadline, Event, and Todo.
 */
public abstract class Task {

    private String description;
    private boolean isMarked;

    /**
     * Constructs task.
     * @param description Task description.
     * @param isMarked Indicates whether task is marked/done.
     */
    public Task(String description, boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    /**
     * Constructs task.
     * @param description Task description.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        isMarked = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        isMarked = false;
    }

    /**
     * Marks this task as not done.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks this task as not done.
     *
     * @return Task description.
     */
    public boolean isMarked() {
        return isMarked;
    }

    /**
     * Checks if a string exists in this task's description.
     *
     * @return True if string is found and false otherwise.
     */
    public boolean contains(String str) {
        return description.matches(".*?\\b" + str + "\\b.*?");
    }

    /**
     * Returns string representation of this class.
     *
     * @return String representation of this task.
     */
    public abstract String toString();

}
