package stevie.task;

import java.text.SimpleDateFormat;

/**
 * Represents a simple task.
 */
public abstract class Task {
    protected static SimpleDateFormat formatter
            = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    /**
     * Name of the task
     */
    protected String name;
    /**
     * Status of the task
     */
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks task as done.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void undone() {
        this.isDone = false;
    }

    /**
     * Returns a string representing the task's name.
     *
     * @return the name of the task
     */
    @Override
    public String toString() {
        String status = isDone ? "[X] " : "[ ] ";
        return status + this.name;
    }

    public abstract String generateTaskSaveData();
}
