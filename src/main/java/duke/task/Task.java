package duke.task;

public abstract class Task {
    protected String title;
    protected Boolean isDone;
    protected TaskType type;

    /**
     * Superclass constructor for tasks.
     *
     * @param title Title of task
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Superclass constructor for tasks with specified done state.
     *
     * @param title Title of task
     * @param isDone Done state
     */
    public Task(String title, Boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    /**
     * Getter for task title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Mark tasks as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark tasks.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Obtain the initial for the type of task.
     *
     * @return String with the initial of the task type
     */
    public String getType() {
        return this.type.getInitial();
    }

    /**
     * Obtain the indication for done state in a string for printing.
     *
     * @return Done indicator, X if the task is done and nothing if the task is undone
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Concatenate task to general format for saving to file.
     *
     * @return Stirng for file saving
     */
    public String toOutputLine() {
        return this.getType() + " | " + (isDone ? "1 " : "0 ") + "| " + this.title;
    }

    /**
     * Use title as the String representation of the task.
     *
     * @return Title of task
     */
    @Override
    public String toString() {
        return this.title;
    }
}