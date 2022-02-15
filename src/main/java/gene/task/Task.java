package gene.task;

import java.time.LocalDateTime;

public abstract class Task {
    private final String taskTitle;
    private final boolean markedStatus;

    /**
     * constructor for generic task
     * @param taskTitle Title of task
     */
    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
        this.markedStatus = false;
    }

    /**
     * constructor for generic task with deadline
     * @param taskTitle title of task
     * @param deadline deadline of task
     */
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

    public abstract boolean containsKeyword(String keyword);
}
