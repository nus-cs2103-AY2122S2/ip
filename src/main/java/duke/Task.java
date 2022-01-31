package duke;

/**
 * Represents a task that is created by an user.
 */
class Task {
    enum Type {
        E, T, D
    }

    protected String description;
    protected boolean hasCompleted;

    /**
     * A constructor to create a new task
     * @param description the description of the task created.
     */
    public Task(String description) {
        this.description = description;
        hasCompleted = false;
    }

    /**
     * To return the status of the given task.
     * @return a boolean of the status of the task.
     */
    String getStatus () {
        return hasCompleted ? "[X]" : "[ ]";
    }

    /**
     * Mark this task completed.
     */
    void markDone() {
        this.hasCompleted = true;
    }

    /**
     * Unmark this task completed.
     */
    void unmarkDone () {
        this.hasCompleted = false;
    }

    /**
     * To get the content of the task.
     * @return a string of the content of the task.
     */
    public String getDescription() {
        return this.description;
    }

}