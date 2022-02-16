package kenobi.task;

/**
 * The Task class encapsulates a task with a name and an isDone boolean.
 */
public abstract class Task {
    protected final String name;
    protected boolean isDone;

    public enum Type {
        DEADLINE,
        EVENT,
        TODO,
    }

    /**
     * Constructs a Task with the given name.
     *
     * @param name The name of the task.
     * @throws TaskException if task name is empty.
     */
    public Task(String name) throws TaskException {
        if (name.matches("[\\s]*")) {
            throw new TaskException("Task name cannot be empty");
        }

        this.name = name;
        this.isDone = false;
    }

    /**
     * Sets the task as done.
     *
     * @return the task.
     */
    public Task markAsDone() {
        isDone = true;

        return this;
    }

    /**
     * Sets the task as undone.
     *
     * @return the task.
     */
    public Task markAsUndone() {
        isDone = false;

        return this;
    }

    /**
     * Returns true if the task name contains the specified term.
     *
     * @param term The term with which the task name will be checked.
     * @return true if the task name contains the specified term.
     */
    public boolean hasWord(String term) {
        return name.contains(term);
    }

    /**
     * Returns the type enum corresponding to the Task.
     *
     * @return the type enum corresponding to the Task.
     */
    public abstract Type type();

    /**
     * Returns a save-friendly string representing the Task.
     *
     * @return a ",.," delimited string representing the Task.
     */
    public abstract String toSave();

    /**
     * Returns a ui-friendly string representing the Task.
     *
     * @return a ui-friendly string representing the Task.
     */
    @Override
    public String toString() {
        String check = this.isDone ? "X" : " ";

        return String.format("[%s] %s", check, this.name);
    }
}
