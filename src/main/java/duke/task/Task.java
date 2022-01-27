package duke.task;
public abstract class Task {
    protected boolean isDone;
    protected String task;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
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

    public abstract String toFileFormat();

    public abstract String toString();
}
