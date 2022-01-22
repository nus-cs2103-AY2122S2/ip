public class Todo extends Task {

    public Todo (boolean completed, String task) {
        super(task, completed);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}