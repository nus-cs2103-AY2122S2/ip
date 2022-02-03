package seedu.task;

import java.time.format.DateTimeFormatter;

public class Task {

    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
    private boolean isCompleted;
    private final String description;

    public Task(String description) {
        this(description, false);
    }

    /**
     * Constructor for Task class
     * @param description Description of Task
     * @param isCompleted Marks whether task is completed or not
     */
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
        return FORMATTER;
    }

    public String toFile() {
        return description + "\t" + isCompleted;
    }

    @Override
    public String toString() {
        return "[" + getCompleted() + "] " + description;
    }
}
