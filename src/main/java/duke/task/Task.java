package duke.task;

public class Task {

    private String task;
    private boolean completed;

    public Task(String task, boolean completed) {
        this.task = task;
        this.completed = completed;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public String getTaskName() {
        return this.task;
    }

    @Override
    public String toString() {
        StringBuilder status = new StringBuilder();
        if (this.completed) {
            status.append("[X] ");
        } else {
            status.append("[ ] ");
        }
        return status.append(this.task).toString();
    }
}