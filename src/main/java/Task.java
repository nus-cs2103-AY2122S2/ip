public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    void markAsDone() {
        this.isDone = true;
    }

    void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone
                ? "X"
                : " ";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
