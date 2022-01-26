public abstract class Task implements Saving, Loading {
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

    public Task(boolean isDone, String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X] " : " ] ") + this.taskDescription;
    }
}
