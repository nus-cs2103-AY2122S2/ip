package kidsnd274.duke.taskobjects;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String getCurrentStatus() {
        return "[T]" + super.getCurrentStatus();
    }

    @Override
    public Types getType() {
        return Types.TODO;
    }
}
