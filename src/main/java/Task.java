public class Task {
    private final String task;
    private final boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public Task mark() {
        return new Task(task, true);
    }

    public Task unmark() {
        return new Task(task, false);
    }

    public String getTask() {
        return task;
    }

    public String getStatus() {
        return done? "[✓] " : "[ ] ";
    }
}
