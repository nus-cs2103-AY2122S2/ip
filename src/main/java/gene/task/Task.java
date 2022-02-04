package gene.task;

import java.time.LocalDateTime;

public abstract class Task {
    private final String taskTitle;
    private boolean markedStatus;
    private LocalDateTime deadline;
    //mark/unmarked as classes

    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
        this.markedStatus = false;
    }

    public Task(String taskTitle, LocalDateTime deadline) {
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.markedStatus = false;
    }

    public abstract Task markTask();

    public abstract Task unmarkTask();

    public boolean isMarked() {
        return this.markedStatus;
    }

    public abstract String toString();
}
