package duke.task;

public abstract class Task {

    protected String taskName;
    protected boolean isMarked;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isMarked = false;
    }

    public void setMarked(String status) {
        this.isMarked = status.equals("mark");
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getIsMarked() {
        return isMarked;
    }

    public String getMarkedStatus() {
        return isMarked ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] " + this.getTaskName(), this.getMarkedStatus());
    }

    public abstract String toFile();
}
