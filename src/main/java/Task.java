public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    void done() {
        this.isDone = true;
    }

    void undone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if(this.isDone) {
            return String.format("[X] %s", this.description);
        } else {
            return String.format("[ ] %s", this.description);
        }
    }
}
