package duke.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getTaskData() {
        return "[T]" + super.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
