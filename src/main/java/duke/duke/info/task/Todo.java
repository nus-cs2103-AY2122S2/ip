package duke.info.task;

public class Todo extends Task {

    public Todo(String todo, boolean isComplete) {
        super(todo, "todo", isComplete);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
