public class Task {
    private final String description;
    private boolean isDone = false;

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

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }
}
