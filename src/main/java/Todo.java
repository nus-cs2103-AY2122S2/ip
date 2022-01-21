public class Todo extends Task {
    public Todo (String task, boolean done) {
        super(task, done);
    }

    public String toString() {
        return String.format("[T][%s] %s", this.done ? "X" : " ", this.task);
    }
}