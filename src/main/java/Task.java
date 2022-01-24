public class Task {
    private final String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
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

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
