public abstract class Task {
    protected final String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }
}
