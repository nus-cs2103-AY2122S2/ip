public class Task {
    // attributes
    protected String description;
    protected boolean isDone;

    // constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}