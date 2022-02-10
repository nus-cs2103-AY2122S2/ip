package seedu.task;

import java.time.format.DateTimeFormatter;

/**
 * Task Parent Class that all subtasks are expected to follow
 */
public class Task {

    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
    private boolean isCompleted;
    private String description;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isCompleted) {
        this.isCompleted = isCompleted;
        this.description = description;
    }

    public void mark() {
        isCompleted = true;
    }

    public void unmark() {
        isCompleted = false;
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
        return description + "\t" + isCompleted;
    }

    @Override
    public String toString() {
        return "[" + getCompleted() + "] " + description;
    }
}
