public class Task {
    protected boolean isDone;
    protected String taskDescription;
    protected StringBuilder sb;

    public Task() {
        this.isDone = false;
        this.taskDescription = "";

        sb = new StringBuilder("");
    }

    public Task(String taskDescription) {
        this.isDone = false;
        this.taskDescription = taskDescription;

        sb = new StringBuilder("");
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        sb.setLength(0);
        sb.append("[").append(this.isDone ? "X] " : " ] ").append(this.taskDescription);

        return sb.toString();
    }
}
