package tasks;

/**
 * Class that defines a task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for the Task class.
     * @param description Description for the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to get the isDone parameter.
     * @return Boolean isDone parameter of the task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Setter for the isDone parameter.
     * @param boo The boolean for setting the isDone value.
     */
    public void setIsDone(boolean boo) {
        this.isDone = boo;
    }

    /**
     * Method to return the description for the task.
     * @return String description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Method to get the mark status of a task.
     * @return String the marking status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method to set isDone to true.
     */
    public void isComplete() {
        this.isDone = true;
    }

    /**
     * Method to return the string for the class.
     * @return String for the class
     */
    @Override
     public String toString() {
         return String.format("[%s] %s", getStatusIcon(), description);
     }

}