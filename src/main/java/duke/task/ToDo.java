package duke.task;

/**
 * Represents a todo task in the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class ToDo extends Task {
    /**
     * Constructor to create a ToDo.
     *
     * @param description Description of task stored.
     * @param isDone      If the task is completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * This method returns the type of this task.
     *
     * @return Returns the task type in String.
     */
    protected String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}