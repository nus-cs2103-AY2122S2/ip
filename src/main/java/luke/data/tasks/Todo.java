package luke.data.tasks;

import java.util.Map;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

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
