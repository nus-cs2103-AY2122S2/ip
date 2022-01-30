package tasks;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean boo) {
        this.isDone = boo;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void isComplete() {
        this.isDone = true;
    }

    @Override
     public String toString() {
         return String.format("[%s] %s", getStatusIcon(), description);
     }

}