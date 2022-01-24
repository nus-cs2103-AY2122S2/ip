package duke.task;

public class ToDoTask extends Task {
    public ToDoTask(String name) {
        super(name);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
