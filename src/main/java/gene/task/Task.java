package gene.task;

import java.time.LocalDateTime;

public abstract class Task {
    private final String taskTitle;
    private final boolean markedStatus;

    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
        this.markedStatus = false;
    }

    public Task(String taskTitle, LocalDateTime deadline) {
        this.taskTitle = taskTitle;
        this.markedStatus = false;
    }

    public abstract Task markTask();

    public abstract Task unmarkTask();

    public boolean isMarked() {
        return this.markedStatus;
    }

    public abstract String toString();
}
