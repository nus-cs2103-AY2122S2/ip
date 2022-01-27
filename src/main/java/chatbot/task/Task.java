package chatbot.task;

import java.io.Serializable;

/**
 * Abstract base class for tasks.
 */
public abstract class Task implements Serializable {
    private final String desc;
    private boolean isDone;

    /**
     * Constructs a task with the specified description.
     * @param desc the description of the task
     */
    public Task(String desc) {
        isDone = false;
        this.desc = desc;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    @Override public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", desc);
    }
}