package src.main.java.duke.task;

/**
 * Task is a class that manages the actions that a Task can do such as marking
 * itself as done etc.
 */
public class Task {
    protected String type;
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task that takes in the type of task, description of task and
     * whether the task is marked as done as input to instantiate a new instance of
     * Task
     * 
     * @param type        the type of task
     * @param description the description of the task
     * @param isDone      whether the task is done
     */
    public Task(String type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructor for Task that takes in the type of task and description of task.
     * Task is assumed to be not yet done.
     * 
     * @param type        the type of task
     * @param description the description of the task
     */
    public Task(String type, String description) {
        this(type, description, false);
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not yet done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a boolean value depending on whether the task is done,
     * if it is then the method returns true.
     * 
     * @return true if the task has been marked as done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the type of the task.
     * 
     * @return the type of task
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the description of the task.
     * 
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }
}
