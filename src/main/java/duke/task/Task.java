package duke.task;

/**
 * Represents and encapsulates a Task.
 */
public abstract class Task implements Comparable<Task> {
    protected final String description;
    protected boolean isDone;

    /**
     * Returns a Task object and accepts a String as description.
     *
     * @param description Description of the task.
     */
    protected Task(String description) {
        this.description = description;
        setDone(false);
    }

    /**
     * Sets done variable of duke.task.Task.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Abstact method to be implemented by subclasses for the purpose of saving to a file.
     *
     * @return Formatted String for saving.
     */
    public abstract String formatSave();

    /**
     * Returns the task description.
     * @return description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a String to display done status of task as well as the task description.
     *
     * @return Formatted String.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.description;
    }
}
