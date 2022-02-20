package duke;

/**
 * Represents the ToDo Task object.
 */
public class ToDo extends duke.Task{

    /**
     * Initializes the ToDo object
     * @param description string which describes the task.
     */
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, String tag) {
        super(description, tag);
    }

}
