package seedu.task;

import java.time.format.DateTimeFormatter;

/**
 * The Task class
 */
public class Task implements Comparable<Task> {

    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
    private boolean isCompleted;
    private String description;
    private int priority;
    private String type;

    /**
     * Task constructor
     *
     * @param description Description of the task
     * @param type Type of the task
     */
    public Task(String description, String type) {
        this(description, false, 1, type);
    }

    /**
     * Overloaded constructor with more parameters added
     *
     * @param description Description of the task
     * @param isCompleted Whether the task is completed
     * @param priority The priority level of the task
     * @param type The type of task
     */
    public Task(String description, boolean isCompleted, int priority, String type) {
        this.isCompleted = isCompleted;
        this.description = description;
        this.priority = priority;
        this.type = type;
    }

    /**
     * Marks task as completed
     */
    public void mark() {
        isCompleted = true;
    }

    /**
     * Marks task as not completed
     */
    public void unmark() {
        isCompleted = false;
    }

    /**
     * Sets a priority level for the task
     */
    public void setPriority(int p) {
        priority = p;
    }

    /**
     * Gets the type of task
     *
     * @return The type of task
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the priority level of the task
     *
     * @return The priority level of the task
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Returns the description of the task
     *
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the state of completion of the task
     *
     * @return A "yes"/"no" depending on whether the task is completed
     */
    public String getCompleted() {
        return isCompleted ? "Yes" : "No";
    }

    /**
     * Gets the date format
     *
     * @return date format
     */
    public static DateTimeFormatter getFormatter() {
        return DATE_FORMAT;
    }

    /**
     * Prepares task to be written in save file
     *
     * @return Task as a string to be saved in a save file
     */
    public String toFile() {
        return type + "\t" + description + "\t" + isCompleted + "\t" + priority;
    }

    /**
     * Returns the string representation of the task
     *
     * @return The task as a string
     */
    @Override
    public String toString() {
        return type + "\t" + priority + "\t" + getCompleted() + "\t" + description;
    }

    /**
     * Comparator to compare tasks based on priority level
     *
     * @param t Another task to be compared with
     * @return An integer showing whether the task has a higher/lower priority than another task
     */
    @Override
    public int compareTo(Task t) {
        return t.getPriority() - this.priority;
    }
}
