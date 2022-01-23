public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isMark) {
        super(name, isMark);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return "T|" + super.toData();
    }
}
