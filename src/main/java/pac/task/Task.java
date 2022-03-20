package pac.task;

public abstract class Task {
    private final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    Task markAsDone() {
        this.isDone = true;
        return this;
    }

    Task markAsNotDone() {
        this.isDone = false;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isMarked() {
        return this.isDone;
    }

    abstract public String toWrite();

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
