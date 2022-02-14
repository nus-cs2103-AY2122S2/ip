package seedu.task;

import java.time.format.DateTimeFormatter;

/**
 * Task Parent Class that all subtasks are expected to follow
 */
public abstract class Task implements Comparable<Task> {

    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
    private boolean isCompleted;
    private String description;
    private int priority;
    private String type;

    public Task(String description, String type) {
        this(description, false, 0, type);
    }

    public Task(String description, boolean isCompleted, int priority, String type) {
        this.isCompleted = isCompleted;
        this.description = description;
        this.priority = priority;
        this.type = type;
    }

    public void mark() {
        isCompleted = true;
    }

    public void unmark() {
        isCompleted = false;
    }

    public void setPriority(int p) {
        priority = p;
    }

    public int getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    private String getCompleted() {
        return isCompleted ? "X" : " ";
    }

    public static DateTimeFormatter getFormatter() {
        return DATE_FORMAT;
    }

    /**
     * Gives the string representation of the task to be saved in the save file
     *
     * @return A string representation of the task to be formatted for the save file
     */
    public String toFile() {
        return type + "\t" + description + "\t" + isCompleted + "\t" + priority;
    }

    @Override
    public String toString() {
        return "[" + type + "][" + priority + "][" + getCompleted() + "] " + description;
    }

    /**
     * Sorts tasks by priority level
     *
     * @param t task to be compared
     * @return integer stating if current task has a higher/lower priority than the other task
     */
    @Override
    public int compareTo(Task t) {
        return t.getPriority() - this.priority;
    }
}
