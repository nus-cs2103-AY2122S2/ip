package spark.tasks.tasktypes;

import spark.exceptions.taskmodificationexceptions.TaskAlreadyMarked;
import spark.exceptions.taskmodificationexceptions.TaskAlreadyUnMarked;

public abstract class Task {
    protected String title;
    protected boolean isDone;

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
        this.isDone = isDone;
    }

    /**
     * Returns the title of the Task.
     * @return
     */
    public String getTitle() {
        return title;
    }

    public void mark() throws TaskAlreadyMarked {
        if (this.isDone) {
            throw new TaskAlreadyMarked(this);
        }

        this.isDone = true;
    }

    public void unMark() throws TaskAlreadyUnMarked {
        if (!this.isDone) {
            throw new TaskAlreadyUnMarked(this);
        }

        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    private String getStatusIcon() {
        return this.isDone ? "[X]" : "[]";
    }

    public abstract String encodeTask();

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.getTitle());
    }
}
