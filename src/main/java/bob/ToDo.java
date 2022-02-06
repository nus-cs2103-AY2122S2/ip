package bob;

public class ToDo extends bob.Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String generateSavedEntry() {
        return String.format("T|%s|%s", isDone ? "1" : "0", description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
