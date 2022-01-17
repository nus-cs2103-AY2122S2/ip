public class Task {
    protected String taskName;
    protected boolean isDone;

    Task(String ss) {
        this.taskName = ss;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone?"X":" ", taskName);
    }
}
