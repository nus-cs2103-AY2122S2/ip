/**
 * Represents a generic task.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        String flag = isDone ? "[X] " : "[ ] ";
        return flag + this.description;
    }
}
