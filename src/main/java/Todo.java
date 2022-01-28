public class Todo extends Task {
    public Todo(String description, boolean isMarked) {
        super(description, isMarked);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return "T | " + (this.isMarked ? 1 : 0) + " | " + this.description;
    }
}
