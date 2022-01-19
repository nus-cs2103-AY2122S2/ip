public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        if (this.isDone) {
            return "[X] " + this.description + "\n";
        } else {
            return "[ ] " + this.description + "\n";
        }
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return description;
    }
}
