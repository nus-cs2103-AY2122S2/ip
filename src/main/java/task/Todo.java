package task;

public class Todo extends Task {

    /**
     * Constructs a Todo object.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
