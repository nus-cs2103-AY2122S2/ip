package task;

public class Todo extends Task {
    public Todo (String description, boolean isDone) {
        super(description, isDone);
    }
    public String fileFormat() {
        return String.format("T | %s | %s", getTaskStatus() ? "X" : " ", getDescription());
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getTaskStatus() ? "X" : " ", getDescription());
    }
}
