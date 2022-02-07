package saitama.tasks;

import java.io.FileWriter;
import java.io.IOException;

public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected RecursiveTag recursiveTag;

    Task(String description, RecursiveTag recursiveTag) {
        this.description = description;
        this.recursiveTag = recursiveTag;
        this.isDone = false;
    }

    Task(String description, boolean isDone, RecursiveTag recursiveTag) {
        this.description = description;
        this.recursiveTag = recursiveTag;
        this.isDone = isDone;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    protected String getRecursiveFrequency() {
        if (recursiveTag != null) {
            return recursiveTag.getLabel();
        } else {
            return "";
        }
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public abstract void saveTask(FileWriter fw) throws IOException;

    @Override
    public String toString() {
        return String.format("[%s]%s %s", this.getStatusIcon(), this.getRecursiveFrequency(),
                this.description);
    }
}
