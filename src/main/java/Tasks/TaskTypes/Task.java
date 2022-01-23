package Tasks.TaskTypes;

import SparkExceptions.TaskModificationExceptions.TaskAlreadyMarked;
import SparkExceptions.TaskModificationExceptions.TaskAlreadyUnMarked;

public abstract class Task {
    protected String title;
    protected boolean done;

    /**
     * Creates a new Task.
     *
     * @param title
     */
    protected Task(String title) {
        this.title = title;
    }

    /**
     * Generates a new Task and initialises it with the
     * given completion status.
     *
     * @param isDone
     * @param title
     */
    protected Task(boolean isDone, String title) {
        this.title = title;
        this.done = isDone;
    }

    public String getTitle() {
        return title;
    }

    public void mark() throws TaskAlreadyMarked {
        if (this.done) {
            throw new TaskAlreadyMarked(this);
        }

        this.done = true;
    }

    public void unMark() throws TaskAlreadyUnMarked {
        if (!this.done) {
            throw new TaskAlreadyUnMarked(this);
        }

        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    private String getStatusIcon() {
        return this.done ? "[X]" : "[]";
    }

    public abstract String encodeTask();

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.getTitle());
    }
}
