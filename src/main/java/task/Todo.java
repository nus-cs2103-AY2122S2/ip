package task;

/**
 * Represents task which is entered with Todo Prefix.
 */
public class Todo extends Task {

    /**
     * Instantiates a Todo task with a description.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Instantiates a todo based on stored data. Data will contain details of
     * task status, description and tag.
     *
     * @param data Array representation of data retrieved from the stored file.
     */
    public Todo(String[] data) {
        super(data[1], data[2], data[3]);
    }

    /**
     * Returns the string representation with details
     * on the task type, task status and task description.
     *
     * @return String representation of Task to be displayed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation with details
     * on the task type, task status and task description.
     *
     * @return String representation of task to be stored.
     */
    @Override
    public String toSave() {
        return "T" + super.toSave();
    }
}