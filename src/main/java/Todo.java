public class Todo extends Task {

    public Todo (boolean completed, String task) {
        super(task, completed);
    }

    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
