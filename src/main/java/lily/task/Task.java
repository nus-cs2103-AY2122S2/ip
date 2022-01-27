package lily.task;
import java.io.Serializable;

/**
 * A parent class which Todo, Events and Deadlines derive from
 * 
 * @author Hong Yi En, Ian
 * @version Jan 2022 (AY21/22 Sem 2)
 */
public class Task implements Serializable{
    protected String desc;    
    protected boolean isDone;    

    /**
     * Create a Task.
     * @param desc What the Task is about.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Mark the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Mark the task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    private String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "] ";
    }

    /**
     * Returns the Task's description and whether it is done
     * @return Task in the form of "[ ] Description"
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.desc;
    }
}