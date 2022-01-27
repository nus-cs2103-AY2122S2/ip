package duke.task;

public class TodoTask extends Task {

    public TodoTask(String title) {
        super(title);
        this.type = TaskType.TODO;
    }

    public TodoTask(String title, Boolean isDone) {
        super(title, isDone);
        this.type = TaskType.TODO;
    }
}
