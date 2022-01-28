package duke.task;

/**
 * A class that represents a ToDo task.
 */
public class ToDo extends Task {
    /**
     * Constructor to initialize an instance of ToDo class with task
     * description.
     *
     * @param description Description of the ToDo task
     */
    public ToDo(String description) {
        super(TaskType.TODO, description);
    }

    /**
     * Constructor to initializes an instance of ToDo class with task
     * description and isDone flag.
     *
     * @param description Description of the ToDo task
     * @param isDone Flag to indicate if the ToDo task is done
     */
    public ToDo(String description, boolean isDone) {
        super(TaskType.TODO, description, isDone);
    }
}
