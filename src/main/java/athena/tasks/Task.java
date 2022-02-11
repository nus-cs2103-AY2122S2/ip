package athena.tasks;

/**
 * Represents a single task object on a task list.
 */
public abstract class Task {
    public static final String SAVE_SEPARATOR = "|";
    private final String name;
    private boolean isDone;

    /**
     * Initializes a new task object with the given name.
     *
     * @param name The name of the task object to be created.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the character icon corresponding to the given task object.
     *
     * @return The character icon of the given task instance.
     */
    public abstract String getIcon();

    /**
     * Returns the task object in a save-friendly format, for easy
     * saving and loading.
     *
     * @return Task object in a save-friendly format.
     */
    public String getSaveFormat() {
        String isDoneString = (isDone ? "1" : "0");
        return String.join(SAVE_SEPARATOR, getIcon(), isDoneString, name);
    }


    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String completionMark = (this.isDone ? "X" : " ");
        return String.format("[%s][%s] %s", getIcon(), completionMark, this.name);
    }
}
