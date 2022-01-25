package duke.task;

public class Todo extends Task {

    public Todo(String name) {
        this(name, false);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String convertToFileFormat() {
        if (isDone) {
            return "T | 1 | " + name;
        }
        return "T | 0 | " + name;
    }
}
