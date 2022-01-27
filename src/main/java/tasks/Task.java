package tasks;

import java.util.Locale;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() { return (isDone ? "X" : " "); }

    public String getStatusNumber() { return (isDone ? "1" : "0"); }

    public void markIsDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Getter method to return the description of a task
     * @return description of task
     */
    public String getDescription() { return this.description; }

    public String toFileFormat() {
        return "," + getStatusNumber() + "," + this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }




}

