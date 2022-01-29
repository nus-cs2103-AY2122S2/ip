public class Task implements java.io.Serializable {
    private boolean isDone;
    private final String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return String.format("[%s] %s",  this.getStatus(), this.description);
    }
}
