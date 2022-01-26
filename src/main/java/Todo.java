public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    @Override
    public String toMemory() {
        return "T" + super.toMemory();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
