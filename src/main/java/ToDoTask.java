public class ToDoTask extends Task {
    ToDoTask(String name) {
        super(name);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
