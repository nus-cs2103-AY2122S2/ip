package athena.tasks;

/**
 * Represents a single todo item in a task list.
 */
public class Todo extends Task {
    /**
     * Constructs a new todo object with the given name.
     *
     * @param name The name of the todo object.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getIcon() {
        return "T";
    }
}
