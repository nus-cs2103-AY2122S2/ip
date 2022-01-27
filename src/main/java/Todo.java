public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toSave() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }
}
