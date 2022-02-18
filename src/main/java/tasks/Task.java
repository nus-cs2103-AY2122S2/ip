package tasks;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }


    public void setDone(boolean isDone) { this.isDone = isDone;}

    public boolean getDone() { return this.isDone;}

    @Override
    public String toString() {
        return " " + this.getStatusIcon() + " | " + this.description;
    }
}
