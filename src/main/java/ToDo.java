public class ToDo extends Task {
    public ToDo(int taskId, String name) {
        super(taskId, name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
