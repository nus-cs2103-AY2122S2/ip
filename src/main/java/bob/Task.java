package bob;

/**
 * Parent class of all task types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public void toggleStatus() {
        this.isDone = !this.isDone;
    }

    public void toggleDone() {
        this.isDone = true;
    }

    public void toggleNotDone() {
        this.isDone = false;
    }

    public abstract String generateSavedEntry();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description.strip();
    }
}
