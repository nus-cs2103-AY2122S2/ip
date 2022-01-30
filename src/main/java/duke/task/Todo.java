package duke.task;

/**
 * Todo represents todo tasks. 
 */
public class Todo extends Task {
    
    /**
     * Initializes the Todo task with a task description & boolean representing if it's done.
     * @param description String of task description. 
     * @param done boolean indicating if the task is done. 
     */
    public Todo (String description, boolean done) {
        super(description, done);
    }

    /**
     * Overriden method to print the Todo Task in a custom format.
     * @return A String representing the custom format of a Todo Task. 
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * A getter method to print the Todo Task in a custom format for saving to file. 
     * @return A String representing the custom format of a Todo Task. 
     */
    @Override
    public String toStringSaveData() {
        return String.join(" | ", "T", String.valueOf(done ? 1 : 0), description);
    }

}
