public class Task {
    protected String type = "GENERAL";
    protected String description;
    protected Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void undo() {
        this.isDone = false;
    }

    public String toString() {
        String status = "[" + (isDone ? "X" : " ") + "]";
        return status + " " + this.description;
    }
}
