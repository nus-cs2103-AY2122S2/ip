public class MarkedTask extends Task {
    private final String taskTitle;
    private final String markedTitle;

    public MarkedTask(String taskTitle) {
        super(taskTitle);
        this.taskTitle = taskTitle;
        this.markedTitle = "[X] " + taskTitle;
    }

    public Task unmarkTask() {
        if (this.isMarked()) {
            return new UnmarkedTask(this.taskTitle);
        }
        else return this;
    }

    public Task markTask() {
        return this;
    }

    public boolean isMarked() {
        return true;
    }

    @Override
    public String toString() {
        return markedTitle;
    }
}
