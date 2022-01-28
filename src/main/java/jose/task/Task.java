package jose.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean matchDescription(String query) {
        return description.contains(query);
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    public String formatData() {
        return (isDone ? "1" : "0") + "|" + description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
