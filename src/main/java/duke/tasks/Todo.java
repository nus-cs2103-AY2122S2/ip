package duke.tasks;

/**
 * A type of task which contains only the description of the task.
 */
public class Todo extends Task {

    /**
     * constructor for Todo class.
     *
     * @param description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Obtain the task's details without date formatting
     *
     * @return string of task details
     */
    public String getTaskData() {
        return "[T]" + super.toString();
    }

    /**
     * Obtain the task's details after date formatting
     *
     * @return string of formatted task details
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
