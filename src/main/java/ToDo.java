public class ToDo extends Task {

    public Duke.TaskType type = Duke.TaskType.TODO;
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.done ? "X" : " ", this.taskName);
    }
}