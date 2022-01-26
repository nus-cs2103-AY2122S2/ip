public class DeadlineTask extends Task {
    private final String taskTitle;
    private final String taskType = "D";
    private boolean markedStatus;
    private String deadline;

    public DeadlineTask (String taskTitle, String deadline) {
        super(taskTitle, deadline);
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.markedStatus = false;
    }

    public DeadlineTask(String taskTitle, String deadline, boolean isMarked) {
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
        String deadlineText = "(by: " + deadline + ")";
        String toReturn = "[D]" + mark + " " + this.taskTitle + " " + deadlineText;
        return toReturn;
    }
}