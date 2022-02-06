package gene.task;

public class TodoTask extends Task {
    private final String taskTitle;
    private final String taskType = "T";
    private boolean markedStatus;

    public TodoTask(String taskTitle) {
        super(taskTitle);
        this.taskTitle = taskTitle;
        this.markedStatus = false;
    }

    public TodoTask(String taskTitle, boolean isMarked) {
        super(taskTitle);
        this.taskTitle = taskTitle;
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
        String toReturn = "[T]" + mark + " " + this.taskTitle;
        return toReturn;
    }

    public boolean containsKeyword(String keyword) {
        return this.taskTitle.contains(keyword);
    }
}