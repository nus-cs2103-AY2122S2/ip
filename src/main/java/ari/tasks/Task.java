package main.java.ari.tasks;

import main.java.ari.exception.EmptyCommandException;

public class Task {
    protected boolean isDone = false;
    protected String taskDescription;

    public Task(String message) throws EmptyCommandException {
        if (message.equals("")) {
            throw new EmptyCommandException();
        }

        this.taskDescription = message;
    }

    protected Task() {
        taskDescription = "";
    }

    @Override
    public String toString() {
        String statusDescription = "[ ]";
        if (isDone) {
            statusDescription = "[X]";
        }

        return String.format("%s %s", statusDescription, taskDescription);
    }

    public String writeToFile() {
        int bool = isDone ? 1 : 0;
        return String.format("%d %s", bool, taskDescription);
    }

    public boolean hasDone() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    public String getDescription() {
        return taskDescription;
    }
}