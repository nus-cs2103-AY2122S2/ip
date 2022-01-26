package duke.task;

import java.io.Serializable;

abstract public class Task implements Serializable {
    private String taskName;
    private boolean status;

    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    public void markDone() {
        this.status = true;
    }

    public void markUndone() {
        this.status = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getStatus() {
        return this.status;
    }

    public abstract String toString();

}
