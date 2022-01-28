package Task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUnDone() {
        this.isDone = false;
    }

    public String toSave() {
        if (isDone) {
            return " : 1 : " + this.description;
        } else {
            return " : 0 : " + this.description;
        }
    }

    @Override
    public String toString() {
        String v = "[" + this.getStatusIcon() + "]" + " " + this.description;
        return v;
    }

    public String getDescription() {
        return this.description;
    }
}
