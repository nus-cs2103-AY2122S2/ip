package duke;

/**
 * Abstract class representing a task object to be saved in TaskList
 */
abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * @return "X" if is done, else " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * mark this task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * unmark this task
     */
    public void unmark() {
        this.isDone = false;
    }

    abstract String getTaskType();

    abstract String getDate();

    /**
     * Getter method of description
     *
     * @return description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Converts Task to String form to be printed
     *
     * @return String to be printed to console
     */
    public String toString() {
        return String.format("[%s]%s", getStatusIcon(), this.description);
    }
}
