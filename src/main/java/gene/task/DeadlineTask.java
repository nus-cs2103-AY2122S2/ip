package gene.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private final String taskTitle;
    private final String taskType = "D";
    private boolean markedStatus;
    private final LocalDateTime deadline;

    public DeadlineTask(String taskTitle, LocalDateTime deadline) {
        super(taskTitle, deadline);
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.markedStatus = false;
    }

    public DeadlineTask(String taskTitle, LocalDateTime deadline, boolean isMarked) {
        super(taskTitle, deadline);
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.markedStatus = isMarked;
    }

    public Task markTask() {
        if (!this.markedStatus) {
            this.markedStatus = true;
        }

        return this;
    }

    public Task unmarkTask() {
        if (this.markedStatus) {
            this.markedStatus = false;
        }

        return this;
    }

    @Override
    public String toString() {
        String mark = this.markedStatus ? "[X]" : "[ ]";
        String deadlineText = "(by: "
                + deadline.format(
                        DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy HH:mm a")
        ) + ")";
        String toReturn = "[D]" + mark + " " + this.taskTitle + " " + deadlineText;
        return toReturn;
    }

    public boolean containsKeyword(String keyword) {
        return this.taskTitle.contains(keyword);
    }
}