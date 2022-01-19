public class Task {
    protected String taskName;
    protected boolean isMarked;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isMarked = false;
    }

    public void toggleMarked() {
        this.isMarked = !this.isMarked;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getMarkedStatus() {
        return isMarked ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] " + this.getTaskName(), this.getMarkedStatus());
    }
}
