package duke.task;

/**
 * An abstract class that represents a task.
 */
public abstract class Task {
    private final TaskType type;
    private final String description;
    private boolean isDone;

    /**
     * Constructor to initialize an instance of Task class with task
     * type and description.
     *
     * @param type Type of task
     * @param description Description of the task
     */
    public Task(TaskType type, String description) {
        // By default, the task is not done
        this(type, description, false);
    }

    /**
     * Constructor to initialize an instance of Task class with task
     * type, description and isDone flag.
     *
     * @param type Type of task
     * @param description Description of the task
     * @param isDone Flag to indicate if the task is done
     */
    public Task(TaskType type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon based on whether the task is done.
     *
     * @return ✔ if the task is done, empty otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "[✔]" : "[ ]"); // mark done task with ✔
    }

    /**
     * Sets the isDone flag to true if the task is done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone flag to false if the task is not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Checks if the date of the task is on the specified date.
     * The method will be overridden in TaskWithDateTime class that
     * represents Deadline and Event tasks with date attached.
     *
     * @param dateStr Specified date
     * @return False as a general task does not have any date attached
     */
    public boolean isOnDate(String dateStr) {
        return false;
    }

    /**
     * Checks if the description of the task contains the keyword.
     *
     * @param keyword Keyword
     * @return Flag to indicate if the description of the task contains
     * the keyword
     */
    public boolean hasKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return "[" + type.getAbbreviation() + "] " + getStatusIcon() + " " + description;
    }

    /**
     * Returns the string representation of the task in save format.
     *
     * @return The string representation of the task to be saved
     */
    public String saveFormat() {
        return type.getAbbreviation() + " | " + (isDone ? "1" : "0") + " | " + description;
    }
}
