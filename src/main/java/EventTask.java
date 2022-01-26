public class EventTask extends Task {
    private final String taskTitle;
    private final String taskType = "E";
    private boolean markedStatus;
    private String deadline;

    public EventTask(String taskTitle, String deadline) {
        super(taskTitle, deadline);
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.markedStatus = false;
    }

    public EventTask(String taskTitle, String deadline, boolean isMarked) {
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

    public boolean isMarked() {
        return this.markedStatus;
    }

    @Override
    public String toString() {
        String mark = this.markedStatus ? "[X]" : "[ ]";
        String deadlineText = "(at: " + deadline + ")";
        String toReturn = "[E]" + mark + " " + this.taskTitle + " " + deadlineText;
        return toReturn;
    }
}
