package task;

public class Todo extends Task {
    public Todo (String description, boolean done) {
        super(description, done);
    }
    public String fileFormat() {
        return String.format("T | %s | %s", this.done ? "X" : " ", this.description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.done ? "X" : " ", this.description);
    }
}