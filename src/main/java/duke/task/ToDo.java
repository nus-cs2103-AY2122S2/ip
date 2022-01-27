package duke.task;

/**
 * Represents a ToDo Task that contains a task name.
 */
public class ToDo extends Task {

    /**
     * A constructor that stores the name of the Task.
     *
     * @param taskName The name of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns the String representation of the task.
     *
     * @return A String representation of the task.
     */
    @Override
    public String toString() {
        String done = isComplete() ? "[X]" : "[ ]";
        return "[T]" + done + getTaskName();
    }
}
