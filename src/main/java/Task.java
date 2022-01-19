public class Task {
    protected boolean isDone;
    protected String taskDescription;

    public Task() {
        this.isDone = false;
        this.taskDescription = "";
    }

    public Task(String taskDescription) {
        this.isDone = false;
        this.taskDescription = taskDescription;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X] " : " ] ") + this.taskDescription;
    }
}
