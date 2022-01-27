package duke.task;

/**
 * Represents a to-do task, which is a task with no date or time conditions.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task object.
     *
     * @param taskName The name given to the task.
     * @param status Indicates whether the task has been completed or not.
     */
    public Todo(String taskName, boolean status) {
        super(taskName, status);
    }

    /**
     * Constructs a Todo task object with the completion status initialised to false.
     *
     * @param title The name of the task.
     */
    public Todo(String title) {
        super(title, false);
    }

    /**
     * Returns task name with the '[T]' prefix to identify it as a todo.
     *
     * @return the task as a string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
