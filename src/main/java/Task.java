import java.time.LocalDate;

public class Task {
    protected String task;
    protected boolean done;

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

    public String saveData() {
        return task;
    }

    public LocalDate getDate() {
        return null;
    }

    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return done? "[✓] " + task: "[ ] " + task;
    }
}
