package jose.task;

/**
 * Class that encapsulates a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor that only takes in a description and sets the isDone variable to false by default.
     *
     * @param description Description of a task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Constructor that sets both description and task status.
     *
     * @param description Description of a task.
     * @param isDone Boolean stating whether a task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks whether a query can be found in the task description.
     *
     * @param query The given word or phrase.
     * @return Boolean stating whether the description contains the query.
     */
    public boolean matchDescription(String query) {
        return description.contains(query);
    }

    /**
     * Method to mark a task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Method to mark a task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns an icon representing the status of a task based on the isDone variable.
     *
     * @return An icon representing the status of a task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns a formatted representation of a task that will be saved to the data file.
     *
     * @return A formatted string to be saved to the data file.
     */
    public String formatData() {
        return (isDone ? "1" : "0") + "|" + description;
    }

    /**
     * Returns a string representation of a task.
     *
     * @return A string representation of a task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
