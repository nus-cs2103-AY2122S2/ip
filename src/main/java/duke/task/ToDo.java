package duke.task;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    /**
     * Returns a ToDo Task object and accepts a String as description.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a formatted String to be saved in a file.
     *
     * @return Formatted String for saving.
     */
    @Override
    public String formatSave() {
        return "T |" + (super.isDone ? "1| " : "0| ") + super.description;
    }

    /**
     * Returns a String to display the type, the done status of task as well as the task description.
     *
     * @return Formatted String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof ToDo) {
            return this.description.compareTo(o.description);
        }
        return -1;
    }
}
