public class Task {
    String description;
    boolean isDone;

    public Task(String desc) {
        description = desc;
        isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public void done() {
        isDone = true;
    }

    public void undo() {
        isDone = false;
    }
}
