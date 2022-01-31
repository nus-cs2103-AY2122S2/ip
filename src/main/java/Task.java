/**
 * Representation of a Task object
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Default constructor
     * @param description description of the task
     * @param metaInfo deadline information of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    
    /** 
     * @return String "X" if isDone, else " "
     */
    public String isDone() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    
    /** 
     * @return String Description of the task
     */
    public String getDescription() {
        return description;
    }

    
    /** 
     * Sets isDone = true
     * @return String task description is done!
     */
    public String setDone() {
        this.isDone = true;
        return this.description + " is done!";
    }

    
    /** 
     * Sets isDone = false
     * @return String task description is undone!
     */
    public String setUndone() {
        this.isDone = false;
        return this.description + " is undone!";
    }

    
    /** 
     * @return String "[T]" task icon
     */
    public String getTaskIcon() {
        return "[T]";
    }

    
    /** 
     * @return String default string representation of a task
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.description;
    }
}