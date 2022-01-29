package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public String getDetail() {
        return "";
    }

    @Override
    public String toString(){
        return this.getStatusIcon() + this.description;
    }
}
