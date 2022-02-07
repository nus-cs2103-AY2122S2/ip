package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task object which can be added to the task list.
 */
public class Task {

    static final DateTimeFormatter OUTPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy");
    static final DateTimeFormatter INPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("d/M/uuuu");
    protected String description;
    protected boolean isDone;

    /**
     * Contructor for the Task class.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * This method is to be overridden by the children method
     *
     * @return Task description format for file input.
     */
    public String taskDescriptionForFile() {
        return this.description;
    }

    public DateTimeFormatter getOutputDateFormat() {
        return OUTPUT_DATE_FORMAT;
    }

    public static DateTimeFormatter getInputDateFormat() {
        return INPUT_DATE_FORMAT;
    }
}
