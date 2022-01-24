public class ToDoTask extends Task {
    public ToDoTask(String taskName) {
        super(taskName);
    }
    public ToDoTask(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]" + super.toString());
    }
}
