package duke;

public class TodoTask extends Task {

    public TodoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), name);
    }

    @Override
    public String toStore() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.name);
    }
}
