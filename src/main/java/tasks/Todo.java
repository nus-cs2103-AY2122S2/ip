package tasks;

public class Todo extends Task {

    public Task.TaskType type = Task.TaskType.TODO;
    public String taskName;

    public Todo(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.done ? "X" : " ", this.taskName);
    }

    public String exportToString() {
        return String.format("%s %s %s", this.type, this.taskName, this.done);
    }
}