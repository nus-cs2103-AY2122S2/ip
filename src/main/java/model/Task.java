package model;

public abstract class Task {
    private static final String FINISHED_STRING = "[X]";
    private static final String UNFINISHED_STRING = "[ ]";
    private static final String MARKING_ERROR_MESSAGE = "Task already %sed";
    protected static final String ERROR_MESSAGE = "I don't understand what '%s' means";

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

    public void markComplete() throws TaskNoChangeException {
        if (isFinished) {
            throw(new TaskNoChangeException(String.format(MARKING_ERROR_MESSAGE, "mark")));
        }
        isFinished = true;
    }

    public void markIncomplete() throws TaskNoChangeException {
        if (!isFinished) {
            throw(new TaskNoChangeException(String.format(MARKING_ERROR_MESSAGE, "unmark")));
        }
        isFinished = false;
    }

    @Override
    public String toString() {
        return String.format("%s %s", isFinished ? FINISHED_STRING : UNFINISHED_STRING, task);
    }

    public abstract String getType();

    public String getTaskBody() {
        return task;
    }
}
