package saitama.tasks;

import java.io.FileWriter;
import java.io.IOException;

abstract public class Task {

    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    abstract public void saveTask(FileWriter fw) throws IOException;

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description.toString();
    }
}
