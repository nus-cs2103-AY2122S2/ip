public class UnmarkedTask extends Task {
    private final String taskTitle;
    private final String unmarkedTitle;

    public UnmarkedTask(String taskTitle) {
        super(taskTitle);
        this.taskTitle = taskTitle;
        this.unmarkedTitle = "[ ] " + taskTitle;
    }

    public Task markTask() {
        if (!this.isMarked()) {
            return new MarkedTask(this.taskTitle);
        }
        else return this;
    }

    public Task unmarkTask() {
        return this;
    }

    public boolean isMarked() {
        return false;
    }

    @Override
    public String toString() {
        return unmarkedTitle;
    }
}
