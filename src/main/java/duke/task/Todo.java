package duke.task;

public class Todo extends Task {

    protected Icon icon = Icon.T;

    /**
     * Constructs a {@code Todo} object with the specified description
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a {@code Todo} object with the specified description and status
     * @param description the description of the todo task
     * @param isDone a boolean indicating whether the task is done
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString();
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Todo mark() {
        return new Todo(description, true);
    }

    @Override
    public Todo unmark() {
        return new Todo(description, false);
    }

}
