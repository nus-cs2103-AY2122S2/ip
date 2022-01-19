public class Task {

    private final String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "✓" : "✗";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.name);
    }

}
