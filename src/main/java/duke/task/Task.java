package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task object which can be added to the task list.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected DateTimeFormatter outputDateFormat =
            DateTimeFormatter.ofPattern("MMM d yyyy");
    protected static DateTimeFormatter inputDateFormat =
            DateTimeFormatter.ofPattern("d/M/uuuu");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static DateTimeFormatter getInputDateFormat() {
        return inputDateFormat;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Mark the task.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark the task.
     */
    public void unmark() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Task description that is formatted to be written into the file.
     *
     * @return Task description format for file input.
     */
    public String taskDescriptionForFile() {
        return null;
    }
}