package jose.task;

import jose.DukeException;

/**
 * Class that encapsulates a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    /**
     * A set of predefined priority values.
     */
    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    /**
     * Constructor that only takes in a description and sets the isDone variable to false and low priority by default.
     *
     * @param description Description of a task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        priority = Priority.LOW;
    }

    /**
     * Constructor that sets description, task status and priority.
     *
     * @param description Description of a task.
     * @param isDone Boolean stating whether a task is done.
     * @param priority A priority enum.
     */
    public Task(String description, boolean isDone, Priority priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
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
     * Method to mark a task as not done.
     *
     * @param priority A string representation of a priority value.
     * @throws DukeException If the given string does not match any priority value.
     */
    public void changePriority(String priority) throws DukeException {
        switch (priority) {
        case "low":
            this.priority = Priority.LOW;
            break;
        case "medium":
            this.priority = Priority.MEDIUM;
            break;
        case "high":
            this.priority = Priority.HIGH;
            break;
        default:
            throw new DukeException("Unknown priority. Please enter 'low', 'medium' or 'high'.");
        }
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
     * Returns the numerical presentation of the priority of a task.
     *
     * @return A number representing the priority of a task.
     */
    private String getPriority() {
        switch (priority) {
        case LOW:
            return "1";
        case MEDIUM:
            return "2";
        case HIGH:
            return "3";
        default:
            return "?";
        }
    }

    /**
     * Returns an icon representing the priority of a task based on the priority variable.
     *
     * @return An icon representing the priority of a task.
     */
    public String getPriorityIcon() {
        return "[" + getPriority() + "]";
    }

    /**
     * Returns a formatted representation of a task that will be saved to the data file.
     *
     * @return A formatted string to be saved to the data file.
     */
    public String formatData() {
        return getPriority() + "|" + (isDone ? "1" : "0") + "|" + description;
    }

    /**
     * Returns a string representation of a task.
     *
     * @return A string representation of a task.
     */
    @Override
    public String toString() {
        return getPriorityIcon() + getStatusIcon() + " " + description;
    }
}
