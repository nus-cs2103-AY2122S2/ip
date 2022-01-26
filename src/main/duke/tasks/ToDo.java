package duke.tasks;

/**
 * Represents a todo task, is a subclass of task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String simpleString() {
        String flag = isDone ? "1" : "0";
        return "T" + flag + description;
    }
}
