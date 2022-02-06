package duke.task;

public class Todo extends Task {
    /**
     * Default Constructor
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
    }
    
    /**
     * This constructor initializes Todo objects with isDone specified
     * @param isDone whether this task is done or not
     * @param description description of the task
     */
    public Todo(boolean isDone, String description) {
        super(description);
        if (isDone) {
            setDone();
        } else {
            setUndone();
        }
    }
}