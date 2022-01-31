package spark.tasks.tasktypes;

import spark.exceptions.taskmodificationexceptions.TaskAlreadyMarked;
import spark.exceptions.taskmodificationexceptions.TaskAlreadyUnMarked;

/**
 * Represents an item on the user's task-list.
 * All valid items that can be stored in the user's task-list are
 * sub-classes of this class.
 */
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

    /**
     * Marks the Task as completed.
     *
     * @throws TaskAlreadyMarked if the task is already completed.
     */
    public void mark() throws TaskAlreadyMarked {
        if (this.isDone) {
            throw new TaskAlreadyMarked(this);
        }

        this.isDone = true;
    }

    /**
     * Marks the Task as incomplete.
     *
     * @throws TaskAlreadyUnMarked if the task is not complete.
     */
    public void unMark() throws TaskAlreadyUnMarked {
        if (!this.isDone) {
            throw new TaskAlreadyUnMarked(this);
        }

        this.isDone = false;
    }

    /**
     * Checks if the Task is completed and returns true if so.
     *
     * @return true if the task has been completed.
     */
    public boolean isDone() {
        return this.isDone;
    }

    private String getStatusIcon() {
        return this.isDone ? "[X]" : "[]";
    }

    /**
     * Returns an encoded-representation of the Task that can be
     * stored in a text-file and decoded into a Task.
     *
     * @return a String containing the encoded-representation of the Task.
     */
    public abstract String encodeTask();

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.getTitle());
    }
}
