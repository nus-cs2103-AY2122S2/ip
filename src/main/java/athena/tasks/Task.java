package athena.tasks;

import java.time.LocalDate;

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
     * Returns true if the task falls within the given time period.
     * If the task has no attached datetime information, always returns false.
     *
     * @param startDate The startDate of the time period.
     * @param endDate The endDate of the time period
     * @return True if task falls within given time period, including the endDate.
     */
    public abstract boolean isFallingBetweenInclusive(LocalDate startDate, LocalDate endDate);

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
