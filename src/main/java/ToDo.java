public class ToDo extends Task{
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
    public String toString() {
        return "["+Type.T+"]" + super.toString();
    }
}
