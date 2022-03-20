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

    public String toString() {
        if (tag != null){
            return String.format("[T] [%s] %s (%s)", getStatusIcon(), description, tag);
        } else {
            return String.format("[T] [%s] %s", getStatusIcon(), description);
        }

    }

}
