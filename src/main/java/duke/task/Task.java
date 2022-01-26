package duke.task;

import java.time.format.DateTimeFormatter;

public class Task {

    protected String description;
    public boolean isDone;
    protected DateTimeFormatter outputDateFormat =
            DateTimeFormatter.ofPattern("MMM d yyyy");
    protected static DateTimeFormatter inputDateFormat =
            DateTimeFormatter.ofPattern("d/M/uuuu");

    public static DateTimeFormatter getInputDateFormat() {
        return inputDateFormat;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void mark() {
        this.isDone = true;

    }

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

    public String taskDescriptionForFile() {
        return null;
    }
}