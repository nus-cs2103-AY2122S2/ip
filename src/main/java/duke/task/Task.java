package duke.task;

import java.io.Serializable;

public abstract class Task implements Serializable {
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

    public boolean isComplete() {
        return this.status;
    }

    public abstract String toString();

}
