package duke.task;

public class Todo extends Task {
    public Todo(String taskName, boolean status) {
        super(taskName, status);
    }

    public Todo(String title) {
        super(title, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
