package luke.data.tasks;

import java.util.Map;

/**
 * Implements a recurring Todo task.
 */
public class RecurTodo extends RecurringTask {

    /**
     * Constructs a recurring todo task with the specified description.
     *
     * @param description The specified description for the task.
     * @param every       The recurring duration.
     * @param next        The next date to recur from.
     */
    public RecurTodo(String description, String every, String next) {
        super(description, every, next);
    }

    /**
     * Constructs a recurring todo task with the argument map.
     *
     * @param args The map containing the argument required by Todo class.
     */
    public RecurTodo(Map<String, String> args) {
        this(args.get("description"), args.get("every"), args.get("next"));
    }

    @Override
    public String toString() {
        return String.format(super.toString(), "[T]", "");
    }

    @Override
    public String getCommandString() {
        String baseFormat = super.getCommandString();
        String taskFormat = String.format("todo %s", description);
        return String.format(baseFormat, taskFormat);
    }
}
