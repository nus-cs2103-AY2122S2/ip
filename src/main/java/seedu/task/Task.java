package seedu.task;

import java.time.format.DateTimeFormatter;

public abstract class Task implements Comparable<Task> {

    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
    private boolean isCompleted;
    private String description;
    private int priority;
    private String type;

    public Task(String description, String type) {
        this(description, false, 1, type);
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

    public String getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public String getCompleted() {
        return isCompleted ? "Yes" : "No";
    }

    public static DateTimeFormatter getFormatter() {
        return DATE_FORMAT;
    }

    public String toFile() {
        return type + "\t" + description + "\t" + isCompleted + "\t" + priority;
    }

    @Override
    public String toString() {
        return type + "\t" + priority + "\t" + getCompleted() + "\t" + description;
    }

    @Override
    public int compareTo(Task t) {
        return t.getPriority() - this.priority;
    }
}
