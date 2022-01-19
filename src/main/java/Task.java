public abstract class Task {
    private boolean isDone;
    protected String task;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String statusString() {
        return isDone ? "[X]" : "[ ]";
    }

    public abstract String toString();
}
