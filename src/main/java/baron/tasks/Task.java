package baron.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public boolean mark() {
        if (this.isDone) {
            return false;
        }
        this.isDone = true;
        return true;
    }

    public boolean unmark() {
        if (this.isDone) {
            this.isDone = false;
            return true;
        }
        return false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
