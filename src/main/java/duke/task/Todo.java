package duke.task;

/**
 * Todo class represents tasks without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Class constructor specifying the task's description.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }

    /**
     * Returns type and description of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}