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

    public boolean getIsDone() {return this.isDone;}

    public boolean setIsDone(boolean isDone) {return this.isDone = isDone;}

    @Override
    public String toString() {
        return " " + this.getStatusIcon() + " | " + this.description;
    }
}
