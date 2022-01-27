package tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    private Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public Todo clone() {
        return new Todo(super.description, super.isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
