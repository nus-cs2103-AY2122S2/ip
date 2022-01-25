package tasks;
public class Task {
    protected boolean isDone = false;
    protected String taskName = "";

    ////////////////////////////////////////////////////////////////
    // Constructors
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    ////////////////////////////////////////////////////////////////
    // Getters
    public String getTaskName() {
        return this.taskName;
    }
    
    public boolean getIsDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    
    ////////////////////////////////////////////////////////////////
    // Methods
    public void markTask(boolean bool) {
        this.isDone = bool;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }
}