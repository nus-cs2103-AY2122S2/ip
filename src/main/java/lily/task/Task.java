package lily.task;

import lily.LilyException;

import java.io.Serializable;

/**
 * A parent class which Todo, Events and Deadlines derive from.
 * 
 * @author Hong Yi En, Ian
 * @version Jan 2022 (AY21/22 Sem 2)
 */
public class Task implements Serializable{
    private String desc;    
    private boolean isDone;    

    /**
     * Creates a Task.
     * 
     * @param description What the Task is about.
     */
    public Task(String description) {
        desc = description;
        isDone = false;
    }

    /**
     * Returns the description of the task.
     * 
     * @return The description of the Task as a String.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Marks the task as done.
     * 
     * @return this task after it is done
     * @throws LilyException If user has finished the task already.
     */
    public Task mark() throws LilyException {
        if (isDone) {
            throw new LilyException(LilyException.ERROR_ALREADY_MARKED);
        }

        isDone = true;
        return this;
    }

    /**
     * Unmarks the task as undone.
     * 
     * @return this task after it is undone
     * @throws LilyException If user hasn't done the task yet.
     */
    public Task unmark() throws LilyException {
        if (!isDone) {
            throw new LilyException(LilyException.ERROR_ALREADY_UNMARKED);
        }

        isDone = false;
        return this;
    }

    /**
     * Gives a string which indicates whether the task is done or not.
     * 
     * @return X if the task is done.
     */
    public String getStatusIcon() {
        String icon = isDone ? "X" : " ";
        return "[" + icon + "] ";
    }

    /**
     * Returns the Task's description and whether it is done
     * 
     * @return Task in the form of "[ ] Description"
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.desc;
    }
}