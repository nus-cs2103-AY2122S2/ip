package seedu.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String toFile() {
        return "T\t" + super.toFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
