public class Todo extends Task {

    Todo(String description) {
        super(description, "T", null);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
