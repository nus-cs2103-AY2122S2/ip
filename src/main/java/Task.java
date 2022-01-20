public class Task {
    private final String task;
    private final boolean completed;

    public Task(String task) {
        this.task = task;
        this.completed = false;
    }

    public Task(String task, boolean completed) {
        this.task = task;
        this.completed = completed;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public String getTaskName() {
        return this.task;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[x] " + task;
        } else {
            return "[ ] " + task;
        }
    }
}
