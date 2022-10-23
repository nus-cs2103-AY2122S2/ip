package luke.data.tasks;

import java.util.Map;

/**
 * Implements a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructs an todo task with the specified description.
     *
     * @param description The specified description for the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a todo task with the argument map.
     *
     * @param args The map containing the argument required by Todo class.
     */
    public Todo(Map<String, String> args) {
        this(args.get("description"));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getCommandString() {
        return String.format("todo %s", description);
    }
}
