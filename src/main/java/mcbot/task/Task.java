package mcbot.task;

public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String task){
        this.taskName = task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getTaskIcon() {
        return " ";
    }

    public void markDone() {
        this.isDone = true;
    }

    public void undoDone() {
        this.isDone = false;
    }

    public boolean isMarked() {
        return isDone;
    }
    
    public String toDataString() {
        return "";
    }
    
    public String getTaskName() {
        return taskName;
    }
    
    @Override
    public String toString() {
        return "[" + getTaskIcon() + "][" + getStatusIcon() + "] " + taskName;
    }
}
