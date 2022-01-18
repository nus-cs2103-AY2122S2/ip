public class Task {
    protected String description;
    protected TaskStatus status;
    
    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
    }
    
    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }
    
    public void markAsNotDone() {
        this.status = TaskStatus.NOT_DONE;
    }
    
    public String getStatusIcon() {
        return this.status.getStatusIcon();
    }
    
    public String fancyPrint() {
        return this.getStatusIcon() + " " + this.toString();
    }
    
    @Override
    public String toString() {
        return this.description;
    }
}