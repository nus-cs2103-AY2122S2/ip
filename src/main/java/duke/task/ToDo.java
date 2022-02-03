package src.main.java.duke.task;

/**
 * ToDo is a Task with no specific start or end date or time.
 */
public class ToDo extends Task {
    private static String type = "T";

    /**
     * Constructor for ToDo task takes in the description of the task and whether it
     * has been marked as done.
     * 
     * @param description the description of the ToDo task
     * @param isDone      true if the task has been marked as done
     */
    public ToDo(String description, boolean isDone) {
        super(type, description, isDone);
    }

    /**
     * Constructor for ToDo task takes in the description of the task.
     * 
     * @param description the description of the ToDo task
     */
    public ToDo(String description) {
        super(type, description);
    }

    /**
     * toString method is overriden to ensure that the String representation of the
     * ToDo task is readable
     */
    @Override
    public String toString() {
        return this.isDone ? "[T][X] " + this.description
                : "[T][ ] " + this.description;
    }
}
