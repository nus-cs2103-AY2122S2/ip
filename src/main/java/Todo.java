public class Todo extends Task {
    public Todo() {
        super();
    }

    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
