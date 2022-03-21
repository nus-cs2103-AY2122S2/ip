package duke;

/**
 * Represents a Task
 */
public class Task {
    private String description;
    private final String sym = "task";
    private boolean isDone;
    private String dayString;

    /**
     * Constructor for a task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.dayString = "";
    }

    /**
     * Returns the status of the task.
     *
     * @return A String "X" if task is done.
     */
    public String getStatusIcon() {
        if (isDone) {
            return "X";
        } else {
            assert(isDone == false);
            return " ";
        }
    }

    /**
     * Retreives the symbol of Task
     */
    public String getSym() {
        return sym;
    }

    /**
     * Retreives the day of Task
     */
    public String getDayString() {
        return dayString;
    }

    /**
     * Changes the description of the task
     * @param newDescription The new description of the Task.
     */
    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] added: %s", this.getStatusIcon(), this.getDescription());
    }
}
