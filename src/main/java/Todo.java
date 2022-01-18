public class Todo extends Task {
    Todo(String action) {
        super(action, "todo");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
