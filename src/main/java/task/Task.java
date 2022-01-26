package task;

public abstract class Task {
    protected String description;
    protected TaskStatus status;
    
    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
    }
    //test
    
    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }
    
    public void markAsNotDone() {
        this.status = TaskStatus.NOT_DONE;
    }
    
    public TaskStatus getStatus() {
        return this.status;
    }
    
    public String getStatusIcon() {
        return this.status.getStatusIcon();
    }
    
    public String getDescription() {
        return this.description;
    }
    
    abstract String getTaskIcon();
    
    @Override
    public String toString() {
        return this.getTaskIcon() + " " + this.getStatusIcon() + " " + this.getDescription();
    }
}