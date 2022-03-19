public abstract class Task {
    private final String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    boolean markAsDone() {
        this.isDone = true;
        return this.isDone;
    }

    boolean markAsNotDone() {
        this.isDone = false;
        return this.isDone;
    }

    String getDescription() {
        return this.description;
    }

    abstract String toWrite();

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
