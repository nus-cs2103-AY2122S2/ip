package duke.task;

/**
 * Abstract class to represent the framework of a Task. 
 */
public abstract class Task {
    
    protected String description; 
    protected boolean done;

    /**
     * Initializes the Task object. 
     * @param description String representing the task description. 
     * @param done boolean to indicate if the task is done or not. 
     */
    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    /**
     * Getter method to return the status of a Task.
     * @return A String representing the status of a Task. 
     */
    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    /**
     * Getter method to return the description of a Task. 
     * @return A String representing the description of a Task. 
     */
    public String getDescription() {
        //
        return this.description;
    }

    /**
     * Setter method to mark the status of a Task as done.
     */
    public void markAsDone() {
        if (this.done == true) {
            System.out.println("this item has already been marked as done");
        } else {
            this.done = true;
        }
    }

    /**
     * Setter method to mark the status of a Task as undone.
     */
    public void markAsUndone() {
        if (this.done == false) {
            System.out.println("this item has already been marked as undone");
        } else {
            this.done = false;
        }
    }

    /**
     * Overriden method to print the Task in a custom format.
     * @return A String representing the custom format of a Task. 
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * A getter method to print the Task in a custom format for saving to file. 
     * @return A String representing the custom format of a Task. 
     */
    public abstract String toStringSaveData();

}
