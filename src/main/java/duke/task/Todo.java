package duke.task;

public class Todo extends Task {
    public Todo(String taskName, boolean isComplete) {
        super(taskName, isComplete);
    }

    public Todo(String title) {
        super(title, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
