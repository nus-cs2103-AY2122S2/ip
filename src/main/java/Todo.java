public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    Todo(String isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String fileFormat() {
        return String.format("T | %s\n", super.fileFormat());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}