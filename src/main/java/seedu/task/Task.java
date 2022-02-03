package seedu.task;

import java.time.format.DateTimeFormatter;

public class Task {

    private boolean isCompleted;
    private final String description;
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");

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

    @Override
    public String toString() {
        return "[" + getCompleted() + "] " + description;
    }
}
