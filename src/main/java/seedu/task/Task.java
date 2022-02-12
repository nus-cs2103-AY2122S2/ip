package seedu.task;

import java.time.format.DateTimeFormatter;

/**
 * Task Parent Class that all subtasks are expected to follow
 */
public class Task implements Comparable<Task> {

    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
    private boolean isCompleted;
    private String description;
    private int priority;

    public Task(String description) {
        this(description, false, 0);
    }

    public Task(String description, boolean isCompleted, int priority) {
        this.isCompleted = isCompleted;
        this.description = description;
        this.priority = priority;
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
        return description + "\t" + isCompleted + "\t" + priority;
    }

    @Override
    public String toString() {
        return "[" + priority + "][" + getCompleted() + "] " + description;
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
