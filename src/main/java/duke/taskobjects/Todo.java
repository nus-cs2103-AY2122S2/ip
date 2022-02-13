package duke.taskobjects;

/**
 * A class representing a Todo Task.
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    /**
     * Creates a Todo Task.
     *
     * @param name Task name or description.
     * @param isDone Boolean which shows it task is marked as done or not.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String getCurrentStatus() {
        return "[T]" + super.getCurrentStatus();
    }

    @Override
    public Types getType() {
        return Types.TODO;
    }
}
