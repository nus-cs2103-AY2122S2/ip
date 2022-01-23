package task;

public class Todo extends Task {
    public Todo (String description, boolean isDone) {
        super(description, isDone);
    }
    public String fileFormat() {
        return String.format("T | %s | %s", this.isDone ? "X" : " ", this.description);
    }
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.description);
    }
}