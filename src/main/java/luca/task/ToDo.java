package luca.task;

/**
 * Tasks that does not have any date/time attached to it.
 */
public class ToDo extends Task {

    /**
     * Constructor to create a deadline task.
     *
     * @param description text description of the ToDo.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Outputs the storage string/format for the task.
     *
     * @return string formatted for storage.
     */
    public String getStorageString() {
        return "T | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + "\n";
    }

    /**
     * Outputs the string representation of ToDo with
     * description.
     *
     * @return formatted string representing ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
