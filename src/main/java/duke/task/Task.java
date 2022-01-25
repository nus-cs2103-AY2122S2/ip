package duke.task;

import java.time.format.DateTimeFormatter;

public class Task {

    protected String description;
    public boolean isDone;
    protected String type;

    public DateTimeFormatter getOutputDateFormat() {
        return outputDateFormat;
    }

    protected DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");

    public static DateTimeFormatter getInputDateFormat() {
        return inputDateFormat;
    }

    protected static DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("d/M/uuuu");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void mark() {
        this.isDone = true;

    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public String taskDescriptionForFile() {
        return null;
    }
}