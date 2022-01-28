package Duke;

public class Task {
    protected String identifier;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public char isDone() {
        return this.isDone ? '1' : '0';
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" +getStatusIcon()+ "] " + this.description;
    }

}