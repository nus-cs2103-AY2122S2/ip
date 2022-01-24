package duke.task;

public class Todo extends Task {
    public Todo(String task) {
        super(TaskType.TODO, task);
    }

    protected Todo() {
        this("");
    }
}
