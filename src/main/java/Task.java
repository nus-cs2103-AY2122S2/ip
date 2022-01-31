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
        return description;
    }

    public String setDone() {
        this.isDone = true;
        return this.description + " is done!";
    }

    public String setUndone() {
        this.isDone = false;
        return this.description + " is undone!";
    }

    public String getTaskIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.description;
    }
}