package duke.task;

/**
 * Represents a Todo which is a subclass of Task
 * Includes a dueDate attribute. Overrides toString() from Task
 */
public class Todo extends Task {
    public Todo (String name) {
        super(name);
    }

    /**
     * Returns String representation of Todo
     *
     * @return String of Todo task, eg: [T][1] Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}