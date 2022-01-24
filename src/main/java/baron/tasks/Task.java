package baron.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.strip();
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

    public String toSaveString(String delimiter) {
        String isDoneString = this.isDone ? "1" : "0";
        return isDoneString + delimiter + this.description;
    }
}
