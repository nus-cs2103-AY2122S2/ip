package mcbot.task;

public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Constructs a task with a task name.
     * The default value isDone is set to false.
     * 
     * @param task The task name to be saved as. 
     */
    public Task(String task){
        this.taskName = task;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * X represents a completed task. 
     * A whitespace represents an incomplete task.
     * 
     * @return The status of the task as an Icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the icon representing the task.
     * A whitespace is used to represent a generic task.
     * 
     * @return The task icon representation.
     */
    public String getTaskIcon() {
        return " ";
    }

    /**
     * Sets the isDone to true.
     * This marks the task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }
    
    /**
     * Sets the isDone to false.
     * This marks the task as incomplete.
     */
    public void undoDone() {
        this.isDone = false;
    }

    /**
     * Returns true if the task is completed.
     * 
     * @return The state of the task. 
     */
    public boolean isMarked() {
        return isDone;
    }

    /**
     * Returns a String to be stored into the data text file.
     * 
     * @return The data representation as a String to be stored.
     */
    public String toDataString() {
        return "";
    }

    /**
     * Returns a String that describes the Task.
     * String contains the task icon, status icon and task name.
     * 
     * @return The string describing the task.
     */
    @Override
    public String toString() {
        return "[" + getTaskIcon() + "][" + getStatusIcon() + "] " + taskName;
    }
}
