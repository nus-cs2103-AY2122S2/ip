package baron.tasks;

/**
 * An abstract class that represents a basic task in real-life that contains all the basic
 * characteristics and behaviour of a basic task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} object with the specified description and {@code isDone} is set to false.
     * @param description the description of this task.
     */
    public Task(String description) {
        this.description = description.strip();
        this.isDone = false;
    }

    /**
     * Returns the status icon of this task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks this task as done.
     *
     * @return true if the task is marked successfully, false if the task was already marked.
     */
    public boolean mark() {
        if (this.isDone) {
            return false;
        }
        this.isDone = true;
        return true;
    }

    /**
     * Un-marks this task as not done yet.
     *
     * @return true if the task is un-marked successfully, false if the task was not marked.
     */
    public boolean unmark() {
        if (this.isDone) {
            this.isDone = false;
            return true;
        }
        return false;
    }

    /**
     * Checks if this {@code Task} object is equal to the specified object.
     *
     * @param o an {@code Object} to be compared with.
     * @return true if the specified object equals to this {@code Task} object.
     */
    @Override
    public abstract boolean equals(Object o);

    /**
     * Returns the string representation of this task.
     *
     * @return the string representation of this task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Returns the encoded string to be stored in the storage using the specified delimiter.
     *
     * @param delimiter the string to be inserted in between various information of this task.
     * @return the encoded string to be stored in the storage using the specified delimiter.
     */
    public String toSaveString(String delimiter) {
        String isDoneString = this.isDone ? "1" : "0";
        return isDoneString + delimiter + this.description;
    }
}
