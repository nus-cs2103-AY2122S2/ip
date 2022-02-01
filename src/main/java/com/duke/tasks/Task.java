package com.duke.tasks;

public class Task {
    protected boolean status;
    protected String description;

    public static enum TaskType { TODO, DEADLINE, EVENT }

    /**
     * Constructor for a Todo Task object.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (status ? "X" : " "); // mark done task with X
    }

    public String getSaveDescription() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
