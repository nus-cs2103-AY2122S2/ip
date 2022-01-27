package tasks;

public class Todo extends Task {

    public Task.TaskType taskType = Task.TaskType.TODO;
    public String taskName;

    public Todo(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone() ? "X" : " ", this.taskName);
    }

    public String exportToString() {
        return String.format("%s %s %s", this.taskType, this.taskName, this.isDone());
    }
}