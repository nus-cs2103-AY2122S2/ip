package duke.task;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task.trim());
    }

    public ToDo(String task, boolean done) {
        super(task, done);
    }

    @Override
    public ToDo mark() {
        return new ToDo(task, true);
    }

    @Override
    public ToDo unmark() {
        return new ToDo(task, false);
    }

    @Override
    public String saveData() {
        int done = super.done ? 1 : 0;
        return Type.T + " | " + done + " | " + task;
    }

    @Override
    public String toString() {
        return "[" + Type.T + "]" + super.toString();
    }
}
