public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String task){
        this.taskName = task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markDone() {
        this.isDone = true;
    }

    public void undoDone() {
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }
}
