package duke.task;

public class Todo extends Task {
    protected String by;

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getFileString() {
        return "T|" + (isDone == true ? "1|" : "0|") + getDescription() + "\n";
    }
}
