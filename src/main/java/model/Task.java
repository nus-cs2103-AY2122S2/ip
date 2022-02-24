package model;

public class Task {
    private static final String FINISHED_STRING = "[X]";
    private static final String UNFINISHED_STRING = "[ ]";
    private final String task;
    private boolean isFinished;

    public Task(String task, boolean isFinished) {
        this.task = task;
        this.isFinished = isFinished;
    }

    public Task(String task) {
        this(task, false);
    }

    public boolean getCompletionStatus() {
        return isFinished;
    }

    public void markComplete() {
        isFinished = true;
    }

    public void markIncomplete() {
        isFinished = false;
    }

    @Override
    public String toString() {
        return String.format("%s %s", isFinished ? FINISHED_STRING : UNFINISHED_STRING, task);
    }
}
