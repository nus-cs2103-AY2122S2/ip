package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }

    /**
     *
     * @param description
     * @param done
     */
    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done; 
    }

    public String getStatusIcon() {

        return "[" + (isDone ? "X" : " ") + "]"; // mark done task with X
    }

    public String getName() {

        return this.description;
    }
    
    public void markAsDone() {
        this.isDone = true;
        
        
    }
        
    public void unmark() {
        this.isDone = false;
        
    }

    public int getLength() {
        return description.length();
    }

 
    public boolean isDone() {
        return this.isDone;
    }
   
    public String toString() {

        return getStatusIcon() + description;
    }
    
}