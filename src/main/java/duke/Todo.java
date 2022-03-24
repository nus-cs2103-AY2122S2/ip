package duke;

public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    Todo(String isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toFileFormat() {
        return String.format("T | %s\n", super.toFileFormat());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
