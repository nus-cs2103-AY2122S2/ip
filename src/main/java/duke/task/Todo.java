package duke.task;

/**
 * Represents a Todo object
 */
public class Todo extends Task {
    public static final String TASK_NAME = "Todo";

    /**
     * Default Constructor
     *
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * This constructor initializes Todo objects with isDone specified
     *
     * @param isDone      whether this task is done or not
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
